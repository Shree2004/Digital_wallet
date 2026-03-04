const API_BASE = "http://localhost:8080";
fetch(`${API_BASE}/users/register`)

async function registerUser() {

    const user = {
        userName: document.getElementById("username").value,
        emailId: document.getElementById("email").value,
        mobileNumber: document.getElementById("mobile").value
    };

    const res = await fetch("http://localhost:8080/users/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });

    const data = await res.json();

    alert("User created with ID: " + data.userId);
}

async function checkBalance() {

    const userId = document.getElementById("userId").value;

    const res = await fetch(`http://localhost:8080/wallet/${userId}/balance`);

    const data = await res.json();

    document.getElementById("balance").innerText = "Balance: " + data;
}

async function depositMoney() {

    const userId = document.getElementById("userId").value;
    const amount = document.getElementById("depositAmount").value;

    const res = await fetch(`http://localhost:8080/wallet/${userId}/deposit`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            amount: amount
        })
    });

    const data = await res.json();

    alert("New Balance: " + data);
}

async function withdrawMoney() {

    const userId = document.getElementById("userId").value;
    const amount = document.getElementById("withdrawAmount").value;

    const res = await fetch(`http://localhost:8080/wallet/${userId}/withdraw`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            amount: amount
        })
    });

    const data = await res.json();

    alert("New Balance: " + data);
}

async function getTransactions() {

    const userId = document.getElementById("userId").value;

    const res = await fetch(`http://localhost:8080/wallet/${userId}/transactions`);

    const transactions = await res.json();

    const list = document.getElementById("transactions");

    list.innerHTML = "";

    transactions.forEach(t => {

        const li = document.createElement("li");

        li.innerText = `${t.type} : ${t.amount}`;

        list.appendChild(li);
    });
}