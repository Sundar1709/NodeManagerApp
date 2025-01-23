document.addEventListener('DOMContentLoaded', function () {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = new FormData(form);
            fetch(form.action, {
                method: form.method,
                body: formData
            })
            .then(response => response.text())
            .then(data => {
                if (data.includes('error')) {
                    document.querySelector('.error').innerText = data;
                } else {
                    document.querySelector('.success').innerText = data;
                    setTimeout(() => {
                        window.location.href = '/';
                    }, 2000);
                }
            })
            .catch(error => {
                document.querySelector('.error').innerText = 'An error occurred: ' + error;
            });
        });
    });
});