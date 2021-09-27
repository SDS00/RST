<#import "base.ftlh" as p>
<#import "nav.ftlh" as nav>

<@p.page>
    <header>
        <@nav.nav/>
        <button class="activate-crud-form" type="button">Добавить отдел</button>
    </header>

    <div class="container">
        <#list departments as department>
        <div class="entity-card department" data-id=${department.id}>
            <div class="card-header">
                <h3 class="department__name">${department.name}</h3>
                <div class="group-button">
                    <button class="update-entity">редактировать</button>
                    <button class="delete-entity">удалить</button>
                </div>
            </div>
            <p><small class="department__phone">${department.phone}</small></p>
            <p><small class="department__email">${department.email}</small></p>
            <p><a href="/department/${department.id}">Подробнее</a></p>
        </div>
        <#else>
        <div>
            <p><strong>Нет отделов!</strong></p>
        </div>
        </#list>
    </div>

    <form class="crud-form" action="/department" method="" data-action="/department" data-redirect="/department">
        <input name="name" type="text" placeholder="Название отдела">
        <input name="phone" type="text" placeholder="Номер телефона отдела">
        <input name="email" type="text" placeholder="Адрес электронной почты отдела">
        <input type="submit" value="">
        <button class="close-crud-form" type="button">Закрыть</button>
    </form>

    <script src="/department.js"></script>
</@p.page>