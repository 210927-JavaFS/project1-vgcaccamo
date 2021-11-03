const URL = "http://localhost:8081/";

let loginButton = document.getElementById("loginButton");
let buttonRow = document.getElementById("buttonRow");
let approvalRow = document.getElementById("approvalRow");
let addReimbursementButton = document.getElementById("addReimbursementButton");
let showMyTicketsButton = document.createElement("button");
let showAllTicketsButton = document.createElement("button");
let showPendingTicketsButton = document.createElement("button");
let showApprovedTicketsButton = document.createElement("button");
let showDeniedTicketsButton = document.createElement("button");
let approveTicketId = document.createElement("input");
let approveTicketSubmit = document.createElement("button");
let denyTicketSubmit = document.createElement("button");
let logoutButton = document.createElement("button");

loginButton.onclick = loginUser;
logoutButton.onclick = logoutUser;
addReimbursementButton.onclick = addReimbursement;
showMyTicketsButton.onclick = showMyTickets;
showAllTicketsButton.onclick = showAllTickets;
showPendingTicketsButton.onclick = showPendingTickets;
showApprovedTicketsButton.onclick = showApprovedTickets;
showDeniedTicketsButton.onclick = showDeniedTickets;
approveTicketSubmit.onclick = approveTicket;
denyTicketSubmit.onclick = denyTicket;

showMyTicketsButton.innerText = "Show My Tickets";
showAllTicketsButton.innerText = "Show All Tickets";
showPendingTicketsButton.innerText = "Show Pending Tickets";
showApprovedTicketsButton.innerText = "Show Approved Tickets";
showDeniedTicketsButton.innerText = "Show Denied Tickets";
approveTicketId.type = "number";
approveTicketId.placeholder = "Ticket ID#";
approveTicketId.name = "ticketId";
approveTicketSubmit.innerText = "Approve this ticket";
denyTicketSubmit.innerText = "Deny this ticket";
logoutButton.innerText = "Logout";

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
    sessionStorage.setItem("login", JSON.stringify(login));
    document.getElementsByClassName("formClass")[0].innerHTML = "";
    document.getElementById("loginOrOut").appendChild(logoutButton);
    buttonRow.appendChild(showMyTicketsButton);
    if (login.userRole.id == 2) {
      buttonRow.appendChild(showAllTicketsButton);
      buttonRow.appendChild(showPendingTicketsButton);
      buttonRow.appendChild(showApprovedTicketsButton);
      buttonRow.appendChild(showDeniedTicketsButton);
      approvalRow.appendChild(approveTicketId);
      approvalButtonRow.appendChild(approveTicketSubmit);
      approvalButtonRow.appendChild(denyTicketSubmit);
    }
  } else {
    let para = document.createElement("p");
    para.setAttribute("style", "color:red");
    para.innerText = "LOGIN FAILED";
    document.getElementsByClassName("formClass")[0].appendChild(para);
  }
}

async function logoutUser() {
  await fetch(URL + "logout");
  sessionStorage.clear();
  window.location.reload(false);
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
  let login = JSON.parse(sessionStorage.login);
  let response = await fetch(URL + "reimbursements/author/" + login.id, {
    credentials: "include",
  });

  if (response.status === 200) {
    let data = await response.json();
    populateReimbursementsTable(data);
  } else {
    console.log("Couldn't find any tickets, sorry!");
  }
}

async function showPendingTickets() {
  let response = await fetch(URL + "reimbursements/status/1", {
    credentials: "include",
  });

  if (response.status === 200) {
    let data = await response.json();
    populateReimbursementsTable(data);
  } else {
    console.log("Couldn't find any tickets, sorry!");
  }
}

async function showApprovedTickets() {
  let response = await fetch(URL + "reimbursements/status/2", {
    credentials: "include",
  });

  if (response.status === 200) {
    let data = await response.json();
    populateReimbursementsTable(data);
  } else {
    console.log("Couldn't find any tickets, sorry!");
  }
}

async function showDeniedTickets() {
  let response = await fetch(URL + "reimbursements/status/3", {
    credentials: "include",
  });

  if (response.status === 200) {
    let data = await response.json();
    populateReimbursementsTable(data);
  } else {
    console.log("Couldn't find any tickets, sorry!");
  }
}

async function approveTicket() {
  let response = await fetch(URL + "reimbursements/" + approveTicketId.value, {
    credentials: "include",
  });
  if (response.status === 200) {
    let reimbursement = await response.json();
    reimbursement.resolver = JSON.parse(sessionStorage.login);
    reimbursement.resolved = Date.now();
    reimbursement.status = {
      id: "2",
      status: "approved",
    };
    updateTicket(reimbursement);
  }
}

async function denyTicket() {
  let response = await fetch(URL + "reimbursements/" + approveTicketId.value, {
    credentials: "include",
  });
  if (response.status === 200) {
    let reimbursement = await response.json();
    reimbursement.resolver = JSON.parse(sessionStorage.login);
    reimbursement.resolved = Date.now();
    reimbursement.status = {
      id: "3",
      status: "denied",
    };
    updateTicket(reimbursement);
  }
}

async function updateTicket(data) {
  let response = await fetch(URL + "reimbursements/" + data.id, {
    method: "PUT",
    body: JSON.stringify(data),
    credentials: "include",
  });

  if (response.status === 201) {
    console.log("Ticket updated.");
  } else {
    console.log("Something went wrong submitting your ticket.");
  }
  approveTicketId.value = "";
  document.getElementById("reimbursementBody").innerHTML="";
}

function populateReimbursementsTable(data) {
  let tbody = document.getElementById("reimbursementBody");
  tbody.innerHTML = "";
  for (let reimbursement of data) {
    let row = document.createElement("tr");
    let rowInfo = {
      id: reimbursement.id,
      author: `${reimbursement.author.firstName} ${reimbursement.author.lastName}`,
      amount: reimbursement.amount,
      description: reimbursement.description,
      type: reimbursement.type.type,
      status: reimbursement.status.status,
    };
    let tdId = document.createElement("td");
    let tdAuthor = document.createElement("td");
    let tdAmount = document.createElement("td");
    let tdDescription = document.createElement("td");
    let tdType = document.createElement("td");
    let tdStatus = document.createElement("td");
    tdId.innerText = rowInfo.id;
    tdAuthor.innerText = rowInfo.author;
    tdAmount.innerText = rowInfo.amount;
    tdDescription.innerText = rowInfo.description;
    tdType.innerText = rowInfo.type;
    tdStatus.innerText = rowInfo.status;
    row.appendChild(tdId);
    row.appendChild(tdAuthor);
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
    submitted: Date.now(),
  };
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
    document.getElementById("ticketSubmission").innerText="Ticket submitted."
  } else {
    document.getElementById("ticketSubmission").innerText="Something went wrong submitting your ticket.";
  }
  document.getElementById("reimbursementBody").innerHTML="";
}
