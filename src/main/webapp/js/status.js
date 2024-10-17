// Get elements
const openModalBtn = document.getElementById("openModalBtn");
const createModal = document.getElementById("createModal");
const closeModalBtn = document.getElementById("closeModalBtn");
const cancelModalBtn = document.getElementById("cancelModalBtn");

// Open modal
openModalBtn.addEventListener("click", function() {
    createModal.style.display = "flex";
});

// Close modal (when clicking the "X" or "Cancel" button)
closeModalBtn.addEventListener("click", function() {
    createModal.style.display = "none";
});

cancelModalBtn.addEventListener("click", function() {
    createModal.style.display = "none";
});

// Close modal when clicking outside the modal content
window.addEventListener("click", function(event) {
    if (event.target === createModal) {
        createModal.style.display = "none";
    }
});