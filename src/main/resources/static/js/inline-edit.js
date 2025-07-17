function startEdit(btn) {
    const row = btn.closest('tr');
    const firstName = row.querySelector('.first-name').innerText.trim();
    const lastName = row.querySelector('.last-name').innerText.trim();
    const email = row.querySelector('.email').innerText.trim();

    row.innerHTML = `
        <td><input type="text" value="${firstName}"></td>
        <td><input type="text" value="${lastName}"></td>
        <td><input type="email" value="${email}"></td>
        <td>
            <button class="save-btn" onclick="saveEdit(this)">Save</button>
            <button class="cancel-btn" onclick="cancelEdit(this)">Cancel</button>
        </td>
    `;
}

function saveEdit(btn) {
    const row = btn.closest('tr');
    const id = row.getAttribute('data-id');
    const inputs = row.querySelectorAll('input');
    const data = {
        firstname: inputs[0].value,
        lastName: inputs[1].value,
        email: inputs[2].value
    };

    fetch('/student/edit/' + id, {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(data)
    }).then(res => {
        if (res.ok) location.reload();
        else alert('Error saving data');
    });
}

function cancelEdit(btn) {
    location.reload();
}

function deleteStudent(btn) {
    if (!confirm('Are you sure you want to delete this student?')) return;
    const row = btn.closest('tr');
    const id = row.getAttribute('data-id');
    fetch('/student/delete/' + id, { method: 'DELETE' })
        .then(res => { if (res.ok) location.reload(); else alert('Error deleting'); });
}

function addStudent() {
    const firstName = document.getElementById('newFirstName').value.trim();
    const lastName = document.getElementById('newLastName').value.trim();
    const email = document.getElementById('newEmail').value.trim();

    if (!firstName || !lastName || !email) {
        alert('All fields are required'); return;
    }

    const data = { firstname: firstName, lastName: lastName, email: email };

    fetch('/student/new', {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(data)
    }).then(res => {
        if (res.ok) location.reload();
        else alert('Error adding student');
    });
}
