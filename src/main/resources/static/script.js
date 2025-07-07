// Global variables
let allTasks = [];

// DOM Elements
const tasksContainer = document.getElementById('tasks');
const createTaskForm = document.getElementById('create-task-form');
const taskNameInput = document.getElementById('task-name');
const taskDescriptionInput = document.getElementById('task-description');
const taskDueDateInput = document.getElementById('task-due-date');

// Initialize the app when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    // Load tasks on page load
    loadTasks();
    
    // Setup form submission
    createTaskForm.addEventListener('submit', handleCreateTask);
});

// Handle form submission for creating tasks
function handleCreateTask(e) {
    e.preventDefault();
    
    const title = taskNameInput.value.trim();
    if (!title) {
        showError('Task title is required');
        return;
    }
    
    const newTask = {
        title: title,
        description: taskDescriptionInput.value.trim(),
        status: 'PENDING',
        dueDate: taskDueDateInput.value || null,
        created_At: new Date().toISOString().split('T')[0] // Current date
    };
    
    createTask(newTask);
}


// API Functions for CRUD operations

// Load and display all tasks
function loadTasks() {
    fetch('/tasks')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(tasks => {
            allTasks = tasks;
            displayTasks(tasks);
        })
        .catch(error => {
            console.error('Error loading tasks:', error);
            showError('Failed to load tasks');
        });
}

// Create a new task
function createTask(task) {
    fetch('/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(task)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(createdTask => {
        console.log('Task created:', createdTask);
        // Clear form
        taskNameInput.value = '';
        taskDescriptionInput.value = '';
        taskDueDateInput.value = '';
        loadTasks(); // Reload tasks
        showSuccess('Task created successfully!');
    })
    .catch(error => {
        console.error('Error creating task:', error);
        showError('Failed to create task');
    });
}

// Update a task
function updateTask(task) {
    fetch('/tasks', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(task)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(updatedTask => {
        console.log('Task updated:', updatedTask);
        loadTasks(); // Reload tasks
        showSuccess('Task updated successfully!');
    })
    .catch(error => {
        console.error('Error updating task:', error);
        showError('Failed to update task');
    });
}

// Delete a task
function deleteTask(id) {
    if (!confirm('Are you sure you want to delete this task?')) {
        return;
    }
    
    fetch('/tasks/' + id, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        console.log('Task deleted');
        loadTasks(); // Reload tasks
        showSuccess('Task deleted successfully!');
    })
    .catch(error => {
        console.error('Error deleting task:', error);
        showError('Failed to delete task');
    });
}

// UI Functions

// Display tasks in the tasks container
function displayTasks(tasks) {
    if (!tasks || tasks.length === 0) {
        tasksContainer.innerHTML = '<p>No tasks found. Create your first task!</p>';
        return;
    }
    
    const tasksHTML = tasks.map(task => `
        <div class="task-item ${task.status.toLowerCase()}" data-task-id="${task.taskId}">
            <div class="task-content">
                <h3 class="task-title">${escapeHtml(task.title)}</h3>
                ${task.description ? `<p class="task-description">${escapeHtml(task.description)}</p>` : ''}
                <div class="task-meta">
                    <span class="task-status status-${task.status.toLowerCase()}">${task.status}</span>
                    ${task.dueDate ? `<span class="task-due-date">Due: ${formatDate(task.dueDate)}</span>` : ''}
                    <span class="task-created">Created: ${formatDate(task.created_At)}</span>
                </div>
            </div>
            <div class="task-actions">
                <select class="status-select" onchange="changeTaskStatus(${task.taskId}, this.value)">
                    <option value="PENDING" ${task.status === 'PENDING' ? 'selected' : ''}>Pending</option>
                    <option value="IN_PROGRESS" ${task.status === 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                    <option value="COMPLETED" ${task.status === 'COMPLETED' ? 'selected' : ''}>Completed</option>
                </select>
                <button class="edit-btn" onclick="editTask(${task.taskId})">Edit</button>
                <button class="delete-btn" onclick="deleteTask(${task.taskId})">Delete</button>
            </div>
        </div>
    `).join('');
    
    tasksContainer.innerHTML = tasksHTML;
}

// Change task status
function changeTaskStatus(taskId, newStatus) {
    const task = allTasks.find(t => t.taskId === taskId);
    if (task) {
        task.status = newStatus;
        updateTask(task);
    }
}

// Edit task function
function editTask(taskId) {
    const task = allTasks.find(t => t.taskId === taskId);
    if (!task) return;
    
    const newTitle = prompt('Edit task title:', task.title);
    if (newTitle && newTitle.trim() !== task.title) {
        task.title = newTitle.trim();
        updateTask(task);
    }
}

// Utility functions
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

function formatDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString();
}

// Show success message
function showSuccess(message) {
    showMessage(message, 'success');
}

// Show error message  
function showError(message) {
    showMessage(message, 'error');
}

// Show a message to the user
function showMessage(message, type) {
    // Remove existing message
    const existingMessage = document.querySelector('.message');
    if (existingMessage) {
        existingMessage.remove();
    }
    
    // Create new message
    const messageDiv = document.createElement('div');
    messageDiv.className = `message ${type}`;
    messageDiv.textContent = message;
    
    // Insert at top of main content
    document.body.insertBefore(messageDiv, document.querySelector('header').nextSibling);
    
    // Auto-remove after 3 seconds
    setTimeout(() => {
        messageDiv.remove();
    }, 3000);
}

