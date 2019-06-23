$(document).ready(() => {
    const restTable = document.getElementById('rest-table').tBodies[0];
    const form = document.getElementById('add-coupon-form');

    const result = {
        addRow: (data) => {
            console.log(JSON.stringify(data, null, 4));

            let tr = document.createElement('tr');
            let id = data.ID;
            let validFrom = data.validFrom.replace('[UTC]', '');
            let validTo = data.validTo.replace('[UTC]', '');
            let info = [
                data.product,
                data.shop.name,
                data.originalPrice,
                data.discountedPrice,
                `${Math.round((1.0 - data.discountedPrice / data.originalPrice) * 10000) / 100}%`,
                new Date(validFrom).toLocaleDateString(),
                new Date(validTo).toLocaleDateString()
            ];
            let cnt = 0;

            for (let i of info) {
                let td = document.createElement('td');
                td.appendChild(document.createTextNode(i));

                if (cnt === 2) {
                    td.classList.add('red-text');
                } else if (cnt === 3) {
                    td.classList.add('green-text');
                }

                tr.appendChild(td);
                cnt++;
            }

            restTable.appendChild(tr);

            let btnDelete = document.createElement('button');

            btnDelete.setAttribute('type', 'button');
            btnDelete.classList.add('btn', 'btn-danger');
            btnDelete.innerText = 'Delete';
            btnDelete.addEventListener('click', e => {
                $.ajax({
                    type: 'DELETE',
                    url: 'api/coupons/' + id,
                    success: item => {
                        if (item === 'true') {
                            restTable.removeChild(tr);
                        }
                    }
                });
            });

            let tdbt = document.createElement('td');
            tdbt.appendChild(btnDelete);
            tr.appendChild(tdbt);
        },

        addCoupon: () => {
            $.post('api/coupons', $('#add-coupon-form').serialize(), data => {
                result.addRow(data);
                $('#add-coupon-form')[0].reset();
            });
        },

        submitForm: (e) => {
            if (e.preventDefault) {
                e.preventDefault();
            }

            result.addCoupon();
            return false;
        },

        fillForm: () => {
            $.get('api/coupons', data => {
                for (let d of data) {
                    result.addRow(d);
                }
            });
        }
    };

    $.get('api/shops', data => {
        // Removes duplicate names from object array.
        let unique = data.filter((elem, index, self) =>
            index === self.findIndex((t) => (
                t.name === elem.name
            ))
        );

        for (let d of unique) {
            $('#shop-list').append(new Option(d.name, d.id));
        }
    });

    result.fillForm();
    form.addEventListener('submit', result.submitForm);
});