$(document).ready(() => {
    const couponsTable = document.getElementById('coupons-table').tBodies[0];
    const url = window.location;
    const shopID = /id=([^&]+)/.exec(url)[1];

    const result = {
        addRow: (data) => {
            let tr = document.createElement('tr');
            let id = data.shop.ID;
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
                    a.setAttribute('href', '/api/shops/' + id);
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

            couponsTable.appendChild(tr);
        },

        fillTable: () => {
            $.get('api/shops/' + shopID, data => {
                for (let d of data.coupons) {
                    result.addRow(d);
                }
            });
        }
    };

    result.fillTable();

    let btnDelete = document.getElementById('delete-shop-btn');
    btnDelete.addEventListener('click', e => {
        $.ajax({
            type: 'DELETE',
            url: 'api/shops/' + shopID,
            success: item => {
                $("#coupons-table tbody").remove();
            },
            error: err => {
                console.error(err, e);
            }
        });
    });
});