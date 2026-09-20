// Admin Logic
function loginAdmin() {
    // Mock login for screenshots
    document.getElementById('login-section').classList.add('hidden');
    document.getElementById('login-section').classList.remove('active');
    document.getElementById('dashboard-section').classList.add('active');
    document.getElementById('dashboard-section').classList.remove('hidden');
}

function addDoctor() {
    alert('Doctor Added successfully to the Smart Clinic Management System!');
}

// Doctor Logic
function loginDoctor() {
    document.getElementById('login-section').classList.add('hidden');
    document.getElementById('login-section').classList.remove('active');
    const dashboard = document.getElementById('dashboard-section');
    dashboard.classList.add('active');
    dashboard.classList.remove('hidden');
    // expand container for dashboard
    document.querySelector('.glass-container').style.width = '500px';
    document.getElementById('docNameDisplay').innerText = 'Doe';
}

// Patient Logic
function loginPatient() {
    document.getElementById('login-section').classList.add('hidden');
    document.getElementById('login-section').classList.remove('active');
    const dashboard = document.getElementById('dashboard-section');
    dashboard.classList.add('active');
    dashboard.classList.remove('hidden');
    // expand container for dashboard
    document.querySelector('.glass-container').style.width = '600px';
}

function searchDoctor() {
    // Display mock results (already in HTML, just simulating an action if needed)
    alert('Found Dr. John Doe (Cardiology)');
}
