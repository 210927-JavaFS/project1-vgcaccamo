const URL = "http://localhost:8081/";

let loginButton = document.getElementById("loginButton");
let buttonRow = document.getElementById("buttonRow");
let showMyTicketsButton = document.createElement("button");
let showAllTicketsButton = document.createElement("button");
let addReimbursementButton = document.getElementById("addReimbursementButton");

loginButton.onclick = loginUser;
showMyTicketsButton.onclick = showMyTickets;
showAllTicketsButton.onclick = showAllTickets;
addReimbursementButton.onclick = addReimbursement;

showMyTicketsButton.innerText = "Show My Tickets";
showAllTicketsButton.innerText = "Show All Tickets";

async function loginUser() {
  let user = {
    username: document.getElementById("username").value,
    password: document.getElementById("password").value,
  };

  let response = await fetch(URL + "login", {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include",
  });

  if (response.status === 200) {
    let login = await response.json();
    console.log(login);
    sessionStorage.setItem("login", JSON.stringify(login));
    console.log(sessionStorage.getItem("login"));
    document.getElementsByClassName("formClass")[0].innerHTML = "";
    buttonRow.appendChild(showAllTicketsButton);
    buttonRow.appendChild(showMyTicketsButton);
  } else {
    let para = document.createElement("p");
    para.setAttribute("style", "color:red");
    para.innerText = "LOGIN FAILED";
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}

async function showAllTickets() {
  let response = await fetch(URL + "reimbursements", {
    credentials: "include",
  });

  if (response.status === 200) {
    let data = await response.json();
    populateReimbursementsTable(data);
  } else {
    console.log("Couldn't find any tickets, sorry!");
  }
}

async function showMyTickets() {
  let response = await fetch(
    URL + "reimbursements/author/" + sessionStorage.getItem("id"),
    {
      credentials: "include",
    }
  );

  if (response.status === 200) {
    let data = await response.json();
    populateReimbursementsTable(data);
  } else {
    console.log("Couldn't find any tickets, sorry!");
  }
}

function populateReimbursementsTable(data) {
  let tbody = document.getElementById("reimbursementBody");
  tbody.innerHTML = "";

  for (let reimbursement of data) {
    let row = document.createElement("tr");
    let rowInfo = {
      id: reimbursement.id,
      amount: reimbursement.amount,
      description: reimbursement.description,
      type: reimbursement.type.type,
      status: reimbursement.status.status,
    };
    let tdId = document.createElement("td");
    let tdAmount = document.createElement("td");
    let tdDescription = document.createElement("td");
    let tdType = document.createElement("td");
    let tdStatus = document.createElement("td");
    tdId.innerText = rowInfo.id;
    tdAmount.innerText = rowInfo.amount;
    tdDescription.innerText = rowInfo.description;
    tdType.innerText = rowInfo.type;
    tdStatus.innerText = rowInfo.status;
    row.appendChild(tdId);
    row.appendChild(tdAmount);
    row.appendChild(tdDescription);
    row.appendChild(tdType);
    row.appendChild(tdStatus);
    tbody.appendChild(row);
  }
}

function getNewReimbursement() {
  let newDescription = document.getElementById("newDescription").value;
  let newAmount = document.getElementById("newAmount").value;
  let newType;
  const rbs = document.querySelectorAll('input[name="newType"]');
  for (const rb of rbs) {
    if (rb.checked) {
      switch (rb) {
        case "lodging":
          newType = {
            id: "1",
            type: "lodging",
          };
          break;
        case "travel":
          newType = {
            id: "2",
            type: "travel",
          };
          break;
        case "food":
          newType = {
            id: "3",
            type: "food",
          };
          break;
        default:
          newType = {
            id: "4",
            type: "other",
          };
          break;
      }
      break;
    }
  }
  let ticket = {
    description: newDescription,
    amount: newAmount,
    type: newType,
    author: JSON.parse(sessionStorage.login),
  };

  console.log(ticket);
  return ticket;
}

async function addReimbursement() {
  let ticket = getNewReimbursement();

  let response = await fetch(URL + "reimbursements", {
    method: "POST",
    body: JSON.stringify(ticket),
    credentials: "include",
  });

  if (response.status === 201) {
    console.log("Ticket submitted.");
  } else {
    console.log("Something went wrong submitting your ticket.");
  }
}
