const crudFormSelector = ".crud-form";

setActivateFormButton(".activate-crud-form", crudFormSelector);
setDeactivateFormButton(".close-crud-form");
setUpdateFormButtons(".update-entity", crudFormSelector, "role", ["name", "salary"]);
setDeleteButtons(".delete-entity", crudFormSelector, "Удалить роль со всеми сотрудниками?");