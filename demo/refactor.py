import os

entities = [
    { "name": "DeviceTypes", "model": "DeviceTypesModel", "id_field": "idDeviceType", "name_field": "deviceTypeName", "path": "device_types", "fields": [("idDeviceType", "ID"), ("deviceTypeName", "Название")] },
    { "name": "Payments", "model": "PaymentsModel", "id_field": "idPayment", "name_field": "paymentMethod", "path": "payments", "fields": [("idPayment", "ID"), ("idRequest", "ID заявки"), ("paymentDate", "Дата"), ("paymentAmount", "Сумма"), ("paymentMethod", "Метод оплаты")] },
    { "name": "Positions", "model": "PositionsModel", "id_field": "idPosition", "name_field": "name", "path": "positions", "fields": [("idPosition", "ID"), ("name", "Название"), ("baseSalary", "Зарплата")] },
    { "name": "RepairRequestsItems", "model": "RepairRequestsItemsModel", "id_field": "idItem", "name_field": "itemName", "path": "repair_request_items", "fields": [("idItem", "ID"), ("requestId", "ID заявки"), ("itemName", "Деталь"), ("quantity", "Количество"), ("price", "Цена")] },
    { "name": "RepairRequests", "model": "RepairRequestsModel", "id_field": "idRequest", "name_field": "description", "path": "repair_requests", "fields": [("idRequest", "ID"), ("description", "Описание"), ("status", "Статус"), ("createdAt", "Дата создания")] },
    { "name": "RepairServices", "model": "RepairServicesModel", "id_field": "idService", "name_field": "name", "path": "repair_services", "fields": [("idService", "ID"), ("name", "Название услуги"), ("price", "Цена")] },
    { "name": "RepairStatuses", "model": "RepairStatusesModel", "id_field": "idStatus", "name_field": "name", "path": "repair_statuses", "fields": [("idStatus", "ID"), ("name", "Статус")] },
    { "name": "Reviews", "model": "ReviewsModel", "id_field": "idReview", "name_field": "comment", "path": "reviews", "fields": [("idReview", "ID"), ("userId", "User ID"), ("comment", "Отзыв"), ("rating", "Оценка")] },
    { "name": "Roles", "model": "RolesModel", "id_field": "idRole", "name_field": "roleName", "path": "roles", "fields": [("idRole", "ID"), ("roleName", "Роль")] },
    { "name": "Users", "model": "UsersModel", "id_field": "idUser", "name_field": "username", "path": "users", "fields": [("idUser", "ID"), ("username", "Имя"), ("email", "Email"), ("roleId", "Role ID")] }
]

base_dir = r"C:\Users\User\Desktop\RPM_SB_16.04\temp_repo\demo\src\main"

for e in entities:
    # 1. Update Controllers
    controller_content = f"""package org.example.demo.controller;

import org.example.demo.model.{e["model"]};
import org.example.demo.repository.{e["name"]}Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/{e["path"]}")
public class {e["name"]}Controller {{

    private final {e["name"]}Repository repo;

    public {e["name"]}Controller({e["name"]}Repository repo) {{
        this.repo = repo;
    }}

    @GetMapping
    public String page(@RequestParam(required = false) Integer searchId, 
                       @RequestParam(required = false) String searchName, 
                       Model model) {{
        List<{e["model"]}> list;
        if (searchId != null) {{
            list = repo.findById(searchId).map(Collections::singletonList).orElse(Collections.emptyList());
        }} else if (searchName != null && !searchName.isEmpty()) {{
            list = repo.findBy{e["name_field"][0].upper() + e["name_field"][1:]}ContainingIgnoreCase(searchName);
        }} else {{
            list = repo.findAll();
        }}
        model.addAttribute("items", list);
        if (!model.containsAttribute("itemObj")) {{
            model.addAttribute("itemObj", new {e["model"]}());
        }}
        return "{e["path"]}";
    }}

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("itemObj") {e["model"]} itemObj, BindingResult result, Model model) {{
        if (result.hasErrors()) {{
            model.addAttribute("items", repo.findAll());
            return "{e["path"]}";
        }}
        repo.save(itemObj);
        return "redirect:/{e["path"]}";
    }}

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {{
        repo.deleteById(id);
        return "redirect:/{e["path"]}";
    }}
}}
"""
    c_path = os.path.join(base_dir, f"java\\org\\example\\demo\\controller\\{e['name']}Controller.java")
    with open(c_path, "w", encoding="utf-8") as f:
        f.write(controller_content)

    # 2. Update HTML templates
    th_fields = ""
    th_headers = ""
    th_cells = ""
    for field, label in e["fields"]:
        if field == e["id_field"]:
            th_headers += f"<th>{label}</th>\n"
            th_cells += f"<td th:text=\"${{item.{field}}}\"></td>\n"
            continue
        th_fields += f"""
        <div class="form-group">
            <label>{label}</label>
            <input type="text" th:field="*{{{field}}}" class="form-control" placeholder="{label}" />
            <div class="error" th:if="${{#fields.hasErrors('{field}')}}" th:errors="*{{{field}}}"></div>
        </div>
        """
        th_headers += f"<th>{label}</th>\n"
        th_cells += f"<td th:text=\"${{item.{field}}}\"></td>\n"

    html_content = f"""<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>{e["name"]} CRM</title>
    <link rel="stylesheet" th:href="@{{/css/styles.css}}">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="sidebar">
        <h2>CRM System</h2>
        <a href="/">Главная</a>
        <a href="/device_types">Устройства</a>
        <a href="/repair_requests">Заявки</a>
        <a href="/repair_services">Услуги</a>
        <a href="/users">Пользователи</a>
        <a href="/roles">Роли</a>
        <a href="/reviews">Отзывы</a>
    </div>

    <div class="main-content">
        <div class="header">
            <h1>Управление: {e["name"]}</h1>
        </div>

        <!-- Поиск -->
        <div class="search-box glass-panel">
            <h3>Поиск</h3>
            <form action="/{e["path"]}" method="get" class="search-form">
                <input type="number" name="searchId" placeholder="Поиск по ID..." />
                <input type="text" name="searchName" placeholder="Поиск по названию..." />
                <button type="submit" class="btn btn-primary">Найти</button>
                <a th:href="@{{/{e["path"]}}}" class="btn btn-secondary">Сброс</a>
            </form>
        </div>

        <!-- Добавление элементов -->
        <div class="form-box glass-panel">
            <h3>Добавить новую запись</h3>
            <form th:action="@{{/{e["path"]}/add}}" th:object="${{itemObj}}" method="post" class="add-form">
                {th_fields}
                <button type="submit" class="btn btn-success">Добавить</button>
            </form>
        </div>

        <!-- Таблица элементов -->
        <div class="table-box glass-panel">
            <h3>Список записей</h3>
            <table>
                <thead>
                    <tr>
                        {th_headers}
                        <th>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    <tr th:each="item : ${{items}}">
                        {th_cells}
                        <td>
                            <form th:action="@{{/{e["path"]}/delete}}" method="post">
                                <input type="hidden" name="id" th:value="${{item.{e['id_field']}}}" />
                                <button type="submit" class="btn btn-danger">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
"""
    h_path = os.path.join(base_dir, f"resources\\templates\\{e['path']}.html")
    with open(h_path, "w", encoding="utf-8") as f:
        f.write(html_content)

print("Generated ALL Controllers and HTML templates")
