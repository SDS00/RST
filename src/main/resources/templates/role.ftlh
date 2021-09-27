<#import "base.ftlh" as p>
<#import "nav.ftlh" as nav>

<@p.page>
    <header>
        <@nav.nav/>
        <button class="activate-crud-form" type="button">Добавить роль</button>
    </header>

    <div class="container">
        <#list roles as role>
        <div class="entity-card role" data-id=${role.id}>
            <div class="card-header">
                <h3 class="role__name">${role.name}</h3>
                <div class="group-button">
                    <button class="update-entity">редактировать</button>
                    <button class="delete-entity">удалить</button>
                </div>
            </div>
            <p class="role__salary">${role.salary}</p>
        </div>
        <#else>
        <div>
            <p><strong>Нет ролей!</strong></p>
        </div>
        </#list>
    </div>

    <form class="crud-form" action="/role" method="" data-action="/role" data-redirect="/role">
        <input name="name" type="text" placeholder="Название роли">
        <input name="salary" class="crud-form-number-field" type="text" placeholder="Зарплата">
        <input type="submit" value="">
        <button class="close-crud-form" type="button">Закрыть</button>
    </form>

    <script src="/role.js"></script>
</@p.page>