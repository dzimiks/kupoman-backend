$(document).ready(() => {
    const restTable = document.getElementById('rest-table').tBodies[0];
    const form = document.getElementById('add-coupon-form');

    const result = {
        addRow: (data) => {
            let tr = document.createElement('tr');
            let id = data.ID;
            let validFrom = data.validFrom.replace('[UTC]', '');
            let validTo = data.validTo.replace('[UTC]', '');
            let sale = (1.0 - data.discountedPrice / data.originalPrice) * 100;
            let info = [
                data.product,
                data.shop.name,
                data.originalPrice,
                data.discountedPrice,
                `${Math.round(sale * 100) / 100}%`,
                new Date(validFrom).toLocaleDateString(),
                new Date(validTo).toLocaleDateString()
            ];
            let cnt = 0;

            for (let i of info) {
                let td = document.createElement('td');

                if (cnt === 1) {
                    let a = document.createElement('a');
                    a.setAttribute('href', '/shop.html?id=' + data.shop.ID);
                    a.appendChild(document.createTextNode(i));
                    td.appendChild(a);
                } else {
                    if (cnt === 2) {
                        td.classList.add('red-text');
                    } else if (cnt === 3) {
                        td.classList.add('green-text');
                    }

                    td.appendChild(document.createTextNode(i));
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
                        restTable.removeChild(tr);
                    }
                });
            });

            let tdbt = document.createElement('td');
            tdbt.appendChild(btnDelete);
            tr.appendChild(tdbt);
        },

        formToJSON: elements => [].reduce.call(elements, (data, element) => {
            data[element.name] = element.value;
            return data;
        }, {}),

        addCoupon: () => {
            $.ajax({
                type: 'POST',
                url: 'api/coupons',
                dataType: 'json',
                contentType: 'application/json',
                data: result.formToJSON(form.elements),
                success: data => {
                    result.addRow(data);
                    $('#add-coupon-form')[0].reset();
                }
            });
        },

        submitForm: (e) => {
            if (e.preventDefault) {
                e.preventDefault();
            }

            result.addCoupon();
            return false;
        },

        fillTable: () => {
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

    result.fillTable();
    form.addEventListener('submit', result.submitForm);
});