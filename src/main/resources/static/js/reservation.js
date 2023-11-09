// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('reservation-id').value;
        fetch(`/api/reservation/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/reservation');
            });
    });
}

// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/reservation/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                roomNumber: document.getElementById('roomNumber').value,
                startTime: document.getElementById('startTime').value,
                endTime: document.getElementById('endTime').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/reservation/${id}`);
            });
    });
}

// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch('/api/reservation/create', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                userName: document.getElementById('name').value,
                roomNumber: document.getElementById('roomNumber').value,
                startTime: document.getElementById('startTime').value,
                endTime: document.getElementById('endTime').value
            })
        })
            .then(() => {
                alert('등록 완료되었습니다.');
                location.replace(`/reservations/${document.getElementById('name').value}`);
            });
    });
}

const cancelButton = document.getElementById('cancel-btn');
if (cancelButton) {
    let currentUrl = window.location.href;
    let pathArray = currentUrl.split('/');
    let id = pathArray[pathArray.length - 1];
    cancelButton.addEventListener('click', event => {
        fetch(`/api/reservation/cancel/${id}`, {
            method: 'GET'
        })
            .then(() => {
                alert('취소가 완료되었습니다.');
                location.replace(`/reservation/${id}`);
            });
    });
}

const approveButton = document.getElementById('approve-btn');
if (approveButton) {
    let currentUrl = window.location.href;
    let pathArray = currentUrl.split('/');
    let userName = pathArray[pathArray.length - 1];
    let id = document.getElementById('reservation-id').innerText.split(':')[1];
    approveButton.addEventListener('click', event => {
        fetch(`/api/reservation/approve/${id}`, {
            method: 'GET'
        })
            .then(() => {
                alert('승인이 완료되었습니다.');
                location.replace(`/reservations/${userName}`);
            });
    });
}

const denyButton = document.getElementById('deny-btn');
if (denyButton) {
    let currentUrl = window.location.href;
    let pathArray = currentUrl.split('/');
    let userName = pathArray[pathArray.length - 1];
    let id = document.getElementById('reservation-id').innerText.split(':')[1];
    denyButton.addEventListener('click', event => {
        fetch(`/api/reservation/deny/${id}`, {
            method: 'GET'
        })
            .then(() => {
                alert('거절이 완료되었습니다.');
                location.replace(`/reservations/${userName}`);
            });
    });
}