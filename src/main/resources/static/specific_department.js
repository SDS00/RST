const crudFormSelector = ".crud-form";

setActivateFormButton(".activate-crud-form", crudFormSelector);
setDeactivateFormButton(".close-crud-form");
setUpdateFormButtons(".update-entity", crudFormSelector, "employee", ["name", "phone", "email"], ["role_id", "department_id"]);
setDeleteButtons(".delete-entity", crudFormSelector, "Удалить сотрудника?");