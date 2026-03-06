const API = "http://localhost:8080";

/* REGISTER USER */
async function registerUser(){

const user = {
userName: document.getElementById("username").value,
emailId: document.getElementById("email").value,
mobileNumber: document.getElementById("mobile").value
};

const res = await fetch(API + "/users/register",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify(user)
});

const data = await res.json();

alert("User created with ID: " + data.userId);

}

/* CHECK BALANCE */
async function checkBalance(){

const userId = document.getElementById("userId").value;

const res = await fetch(API + "/wallet/" + userId + "/balance");

const data = await res.json();

document.getElementById("balance").innerText = "Balance: " + data;

}

/* DEPOSIT */
async function depositMoney(){

const userId = document.getElementById("userId").value;
const amount = document.getElementById("depositAmount").value;

const res = await fetch(API + "/wallet/" + userId + "/deposit",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({amount:amount})
});

const data = await res.json();

alert("New Balance: " + data);

}

/* WITHDRAW */
async function withdrawMoney(){

const userId = document.getElementById("userId").value;
const amount = document.getElementById("withdrawAmount").value;

const res = await fetch(API + "/wallet/" + userId + "/withdraw",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({amount:amount})
});

const data = await res.json();

alert("New Balance: " + data);

}

/* TRANSFER */
async function transferMoney(){

const senderId = document.getElementById("senderId").value;
const receiverId = document.getElementById("receiverId").value;
const amount = document.getElementById("transferAmount").value;

const res = await fetch(API + "/wallet/transfer",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
senderId:senderId,
receiverId:receiverId,
amount:amount
})
});

const data = await res.json();

alert("Transfer complete. Sender balance: " + data);

}
