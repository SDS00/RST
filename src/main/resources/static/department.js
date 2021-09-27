const crudFormSelector = ".crud-form";

setActivateFormButton(".activate-crud-form", crudFormSelector);
setDeactivateFormButton(".close-crud-form");
setUpdateFormButtons(".update-entity", crudFormSelector, "department", ["name", "phone", "email"]);
setDeleteButtons(".delete-entity", crudFormSelector, "Удалить отдел со всеми сотрудниками?");