document.addEventListener('DOMContentLoaded', () => {
    console.log('Frontend connected to backend.');
});

document.getElementById('create-loan-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const loan = {
        borrowerName: document.getElementById('borrowerName').value,
        amount: parseFloat(document.getElementById('amount').value),
        status: document.getElementById('status').value,
    };
    const response = await fetch('http://localhost:8080/api/loans', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loan),
    });
    if (response.ok) {
        alert('Loan created successfully!');
    } else {
        alert('Error creating loan');
    }
});
async function fetchLoans() {
    const response = await fetch('http://localhost:8080/api/loans');
    const loans = await response.json();
    const loanList = document.getElementById('loan-list');
    loanList.innerHTML = loans.map(loan => `
        <div>
            <h3>${loan.borrowerName}</h3>
            <p>Amount: ${loan.amount}</p>
            <p>Status: ${loan.status}</p>
            <button onclick="deleteLoan(${loan.id})">Delete</button>
            <button onclick="showUpdateForm(${loan.id}, '${loan.borrowerName}', ${loan.amount}, '${loan.status}')">Update</button>
        </div>
    `).join('');
}
fetchLoans();
function showUpdateForm(id, borrowerName, amount, status) {
    document.getElementById('update-loan-form').innerHTML = `
        <input type="text" id="updateBorrowerName" value="${borrowerName}" required>
        <input type="number" id="updateAmount" value="${amount}" required>
        <input type="text" id="updateStatus" value="${status}" required>
        <button onclick="updateLoan(${id})">Update Loan</button>
    `;
}

async function updateLoan(id) {
    const loan = {
        borrowerName: document.getElementById('updateBorrowerName').value,
        amount: parseFloat(document.getElementById('updateAmount').value),
        status: document.getElementById('updateStatus').value,
    };
    const response = await fetch(`http://localhost:8080/api/loans/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loan),
    });
    if (response.ok) {
        alert('Loan updated successfully!');
        fetchLoans();
    } else {
        alert('Error updating loan');
    }
}
async function deleteLoan(id) {
    const response = await fetch(`http://localhost:8080/api/loans/${id}`, { method: 'DELETE' });
    if (response.ok) {
        alert('Loan deleted successfully!');
        fetchLoans();
    } else {
        alert('Error deleting loan');
    }
}
