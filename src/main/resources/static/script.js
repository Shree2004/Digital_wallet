const API = "http://localhost:8080";

async function login(){

const userName = document.getElementById("username").value;
const password = document.getElementById("password").value;

const response = await fetch(API + "/auth/login",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
userName:userName,
password:password
})
});

const data = await response.json();

localStorage.setItem("token",data.token);

window.location.href="wallet.html";
}

async function getBalance(){

const token = localStorage.getItem("token");

const response = await fetch(API + "/wallet/balance",{
headers:{
"Authorization":"Bearer " + token
}
});

const balance = await response.text();

document.getElementById("balance").innerText = balance;

}

async function deposit(){

const token = localStorage.getItem("token");

const amount = document.getElementById("depositAmount").value;

await fetch(API + "/wallet/deposit",{
method:"POST",
headers:{
"Content-Type":"application/json",
"Authorization":"Bearer " + token
},
body:JSON.stringify({
amount:amount
})
});

getBalance();

}

async function withdraw(){

const token = localStorage.getItem("token");

const amount = document.getElementById("withdrawAmount").value;

await fetch(API + "/wallet/withdraw",{
method:"POST",
headers:{
"Content-Type":"application/json",
"Authorization":"Bearer " + token
},
body:JSON.stringify({
amount:amount
})
});

getBalance();

}

function logout(){

localStorage.removeItem("token");

window.location.href="login.html";

}

async function transfer(){

const token = localStorage.getItem("token");

const receiverId = document.getElementById("receiverId").value;
const amount = document.getElementById("transferAmount").value;

await fetch(API + "/wallet/transfer",{
method:"POST",
headers:{
"Content-Type":"application/json",
"Authorization":"Bearer " + token
},
body:JSON.stringify({
receiverId:receiverId,
amount:amount
})
});

getBalance();

}

async function register(){

const userName = document.getElementById("username").value;
const emailId = document.getElementById("email").value;
const mobileNumber = document.getElementById("mobile").value;
const password = document.getElementById("password").value;

const res = await fetch(API + "/users/register", {
method: "POST",
headers:{
"Content-Type":"application/json"
},
body: JSON.stringify({
userName:userName,
emailId:emailId,
mobileNumber:mobileNumber,
password:password
})
});

if(res.ok){
alert("User Registered Successfully");
window.location.href="login.html";
}else{
alert("Registration failed");
}

}