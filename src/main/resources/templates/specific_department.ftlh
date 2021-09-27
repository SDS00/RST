<#import "base.ftlh" as p>
<#import "nav.ftlh" as nav>

<@p.page>
    <header>
        <@nav.nav/>
        <button class="activate-crud-form" type="button">Добавить сотрудника</button>
        <div class="header-info">
            <h2>${department.name}</h2>
            <p>
                <small class="department__phone">${department.phone}</small>
                <small class="department__email">${department.email}</small>
            </p>
        </div>
    </header>

    <div class="container">
        <#list employees as employee>
        <div class="entity-card employee" data-id=${employee.id}>
            <div class="card-header">
                <h3 class="employee__name">${employee.name}</h3>
                <div class="group-button">
                    <button class="update-entity">редактировать</button>
                    <button class="delete-entity">удалить</button>
                </div>
            </div>
            <p><small class="employee__phone">${employee.phone}</small></p>
            <p><small class="employee__email">${employee.email}</small></p>
            <p><small class="employee__role">${employee.role.name}</small></p>
        </div>
        <#else>
        <div>
            <p><strong>Нет Сотрудников!</strong></p>
        </div>
        </#list>
    </div>

    <form class="crud-form" action="/employee" method="" data-action="/employee" data-redirect=${"/department/" + department.id}>
        <input name="name" type="text" placeholder="Имя">
        <input name="phone" class="crud-form-number-field" type="text" placeholder="Телефон">
        <input name="email" type="text" placeholder="Электронная почта">
        <select name="role_id">
            <#list roles as role>
            <option value=${role.id}>${role.name}</option>
            </#list>
        </select>
        <select name="department_id">
            <#list departments as department>
                <option value=${department.id}>${department.name}</option>
            </#list>
        </select>
        <input type="submit" value="">
        <button class="close-crud-form" type="button">Закрыть</button>
    </form>


    <script src="/specific_department.js"></script>
</@p.page>