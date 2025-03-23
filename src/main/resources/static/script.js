// Select the elements
const button = document.getElementById('button');
const form = document.getElementById('form');

// Add event listeners
button.addEventListener('click', () => {
  // Handle button click
});

form.addEventListener('submit', (e) => {
  // Handle form submission
  e.preventDefault();
});

// Create a new element
const element = document.createElement('div');

// Set the text content of the element
element.textContent = 'Hello World!';

// Append the element to the DOM
document.body.appendChild(element);

// Use fetch API to make a GET request to retrieve tasks
fetch('/api/tasks')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));

// Use fetch API to make a POST request to create a new task
fetch('/api/tasks', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    title: 'New Task',
    description: 'This is a new task'
  })
})
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));

// Import Handlebars
const Handlebars = require('handlebars');

// Define the template for the To-Do List App
const template = '<ul>{{#tasks}}<li>{{taskName}} ({{status}}) <button class="delete-task">Delete</button></li>{{/tasks}}</ul>';

// Compile the template
const compiledTemplate = Handlebars.compile(template);

// Define the data for the To-Do List App
const data = {
  tasks: [
    { taskName: 'Task 1', status: 'Pending' },
    { taskName: 'Task 2', status: 'Completed' },
  ],
};

// Render the template with the data
const html = compiledTemplate(data);

// Append the rendered HTML to the page
document.body.innerHTML += html;



// Create a new task
function createTask(task) {
    fetch('/api/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(task)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
}

// Read all tasks
function readTasks() {
    fetch('/api/tasks')
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
}

// Update a task
function updateTask(task) {
    fetch('/api/tasks/' + task.id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(task)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
}

// Delete a task
function deleteTask(id) {
    fetch('/api/tasks/' + id, {
        method: 'DELETE'
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
}

