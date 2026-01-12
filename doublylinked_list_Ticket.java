class Ticket {
    String ticketId;
    String customerName;
    String issueDescription;
    String priority;
    int priorityValue;
    Ticket next, prev;
    public Ticket(String ticketId, String customerName, String issueDescription, String priority) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.priority = priority;
        if (priority.equalsIgnoreCase("Critical")) this.priorityValue = 3;
        else if (priority.equalsIgnoreCase("High")) this.priorityValue = 2;
        else this.priorityValue = 1;
    }
}
class TicketQueue {
    private Ticket head, tail;
    public void addTicket(String id, String name, String desc, String priority) {
        Ticket newTicket = new Ticket(id, name, desc, priority);
        if (head == null) {
            head = tail = newTicket;
        } else {
            tail.next = newTicket;
            newTicket.prev = tail;
            tail = newTicket;
        }
        System.out.println("Added: " + id);
    }
    public void addTicketPriority(String id, String name, String desc, String priority) {
        Ticket newNode = new Ticket(id, name, desc, priority);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        if (newNode.priorityValue > head.priorityValue) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }
        Ticket current = head;
        while (current.next != null && current.next.priorityValue >= newNode.priorityValue) {
            current = current.next;
        }
        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) current.next.prev = newNode;
        else tail = newNode;
        current.next = newNode;
    }
    public void resolveTicket() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Resolved: " + head.ticketId);
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
    }
    public void escalateTicket(String ticketId) {
        Ticket target = findNodeById(ticketId);
        if (target != null && target != head) {
            removeNode(target);
            target.next = head;
            head.prev = target;
            head = target;
            System.out.println("Escalated: " + ticketId);
        }
    }
    public void cancelTicket(String ticketId) {
        Ticket target = findNodeById(ticketId);
        if (target != null) {
            removeNode(target);
            System.out.println("Cancelled: " + ticketId);
        }
    }
    public int findTicket(String ticketId) {
        Ticket current = head;
        int pos = 1;
        while (current != null) {
            if (current.ticketId.equals(ticketId)) return pos;
            current = current.next;
            pos++;
        }
        return -1;
    }
    public void listTickets() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }
        Ticket current = head;
        System.out.println("\n--- Ticket Queue ---");
        while (current != null) {
            System.out.println("[" + current.priority + "] ID: " + current.ticketId + " - " + current.customerName);
            current = current.next;
        }
        System.out.println("--------------------\n");
    }
    private void removeNode(Ticket node) {
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;
        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
        node.next = null;
        node.prev = null;
    }

    private Ticket findNodeById(String id) {
        Ticket current = head;
        while (current != null) {
            if (current.ticketId.equals(id)) return current;
            current = current.next;
        }
        return null;
    }
}
public class doublylinked_list_Ticket{
    public static void main(String[] args) {
        TicketQueue queue = new TicketQueue();

        queue.addTicketPriority("T1", "Alice", "Login Issue", "Normal");
        queue.addTicketPriority("T2", "Bob", "Payment Fail", "Critical");
        queue.addTicketPriority("T3", "Charlie", "UI Bug", "High");
        
        queue.listTickets(); 
        
        queue.escalateTicket("T1");
        queue.listTickets(); 
        
        queue.resolveTicket(); 
        queue.listTickets();
    }
}