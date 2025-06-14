$(document).ready(function () {
    loadAllComplaintData();
});
function clearForm() {
    $('#complaint_id').val('');
    $('#title').val('');
    $('#description').val('');
    $('.selected-row').removeClass('selected-row');
}
$('table tbody').on('click', 'tr', function () {
    var cells = $(this).children('td');

    $('#complaint_id').val(cells.eq(0).text()); // set hidden ID
    $('#title').val(cells.eq(1).text());
    $('#description').val(cells.eq(2).text());

    $(this).addClass('selected-row').siblings().removeClass('selected-row');
});
function loadAllComplaintData() {
    const $tbody = $(".complaints-table tbody");
    $tbody.empty(); // clear previous rows

    if (complaintList.length === 0) {
        $tbody.append(`<tr><td colspan="6">No complaints found.</td></tr>`);
    } else {
        complaintList.forEach(c => {
            const row = `
                    <tr>
                        <td>${c.complaintId}</td>
                        <td>${c.title}</td>
                        <td>${c.description}</td>
                        <td>${c.status}</td>
                        <td>${c.remarks}</td>
                        <td>${c.createdAt}</td>
                    </tr>`;
            $tbody.append(row);
        });
    }
}
