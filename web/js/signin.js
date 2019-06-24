$(document).ready(() => {
    const form = document.getElementById('form-signin');

    const result = {
        checkUser: () => {
            $.ajax({
                type: 'POST',
                url: '/api/users',
                dataType: 'json',
                contentType: 'application/json',
                data: form.serialize(), // TODO: ???
                success: data => {
                    console.log(data);
                    $('#form-signin')[0].reset();
                },
                error: err => {
                    console.error(err);
                }
            });
        },

        submitForm: (e) => {
            if (e.preventDefault) {
                e.preventDefault();
            }

            result.checkUser();
            return false;
        }
    };

    form.addEventListener('submit', result.submitForm);
});