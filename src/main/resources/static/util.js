function setActivateFormButton(buttonSelector, formSelector) {
    document.querySelector(buttonSelector)
        .addEventListener("click", () => {
            const form = document.querySelector(formSelector);

            form.querySelector("[type=submit]").value = "Добавить";
            form.method = "POST";

            form.classList.add("active");
        });
}

function setDeactivateFormButton(buttonSelector) {
    document.querySelector(buttonSelector)
        .addEventListener("click", function() {
            const form = this.parentNode;

            form.action = form.dataset.action;
            form.onsubmit = undefined;

            form.classList.remove("active");
        });
}

function setUpdateFormButtons(buttonSelector, formSelector, entity, fields, extraFields) {
    document.querySelectorAll(buttonSelector)
        .forEach(button => button.addEventListener("click", function() {
            const form = document.querySelector(formSelector);
            const entityDom = this.parentNode.parentNode.parentNode;

            form.querySelector("[type=submit]").value = "Обновить";
            form.method = "GET";

            for(const field of fields) {
                form[field].value = entityDom.querySelector("." + entity + "__" + field).innerHTML;
            }

            form.onsubmit = function(e) {
                e.preventDefault();

                const data = {};

                for(const field of fields) {
                    if(form[field].classList.contains("crud-form-number-field")) {
                        form[field].value = form[field].value.replace(/[^\d]/, '');
                    }

                    data[field] = form[field].value;
                }

                if(extraFields) {
                    for(const field of extraFields) {
                        if(form[field].classList.contains("crud-form-number-field")) {
                            form[field].value = form[field].value.replace(/[^\d]/, '');
                        }

                        data[field] = form[field].value;
                    }
                }

                fetch(form.dataset.action + "/" + entityDom.dataset.id, {
                    method: "PUT",
                    headers: { "Content-Type" : "application/json" },
                    body: JSON.stringify(data)
                })
                    .then(() => { form.action = form.dataset.redirect; form.submit(); })
                    .catch(() => alert("Не удалось обновить информацию."));
            };

            form.classList.add("active");
        }));

}

function setDeleteButtons(buttonSelector, formSelector, confirmationMessage) {
    document.querySelectorAll(buttonSelector)
        .forEach(button => button.addEventListener("click", function() {
            const confirmation = confirm(confirmationMessage);

            if(confirmation) {
                const form = document.querySelector(formSelector);
                const entityDom = this.parentNode.parentNode.parentNode;

                form.method = "GET";

                fetch(form.action + "/" + entityDom.dataset.id, {
                    method: "DELETE",
                })
                    .then(() => { form.action = form.dataset.redirect; form.submit(); })
                    .catch(() => alert("Не удалось удалить отдел"));
            }
        }));
}