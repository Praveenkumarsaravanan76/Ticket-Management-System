# Ticket Management System (Doubly Linked List)

This project implements a **Ticket Management System** using a **Doubly Linked List** in Java.
Tickets are handled based on priority and can be escalated, resolved, or cancelled.

## Features
- Add tickets with priority (Critical, High, Normal)
- Automatically sorts tickets based on priority
- Escalate a ticket to the front of the queue
- Resolve (remove) the highest-priority ticket
- Cancel a ticket by ID
- Display all tickets in the queue

## Data Structure Used
- Doubly Linked List

Each ticket node stores:
- Ticket ID
- Customer Name
- Issue Description
- Priority
- Pointers to next and previous tickets

## How to Run
1. Compile the program:
   ```bash
   javac doublylinked_list_Ticket.java
