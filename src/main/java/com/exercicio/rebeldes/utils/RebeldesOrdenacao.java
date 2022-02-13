package com.exercicio.rebeldes.utils;

import com.exercicio.rebeldes.model.Rebelde;
import lombok.*;

import javax.swing.*;

@Data
@NoArgsConstructor
public class RebeldesOrdenacao<T> {

    private node head = null;
    private String orderBy = "";

    static class node {
        Rebelde rebel;
        node next;

        node(Rebelde rebel)
        {
            this.rebel = rebel;
        }
    }

    private node sortedMergeByName(node a, node b)
    {
        node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.rebel.getNome().compareTo(b.rebel.getNome()) < 0) {
            result = a;
            result.next = sortedMergeByName(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMergeByName(a, b.next);
        }
        return result;
    }

    private node sortedMergeByAge(node a, node b)
    {
        node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.rebel.getIdade() <= b.rebel.getIdade()) {
            result = a;
            result.next = sortedMergeByAge(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMergeByAge(a, b.next);
        }
        return result;
    }

    private node sortedMergeByRace(node a, node b)
    {
        node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.rebel.getRaca().ordinal() <= b.rebel.getRaca().ordinal()) {
            result = a;
            result.next = sortedMergeByRace(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMergeByRace(a, b.next);
        }
        return result;
    }

    public void mergeSort(){
        this.head = internalMergeSort(this.head);
    }

    private node internalMergeSort(node h)
    {
        // Base case : if head is null
        if (h == null || h.next == null) {
            return h;
        }

        node middle = getMiddle(h);
        node nextofmiddle = middle.next;

        middle.next = null;

        node left = internalMergeSort(h);

        node right = internalMergeSort(nextofmiddle);

        node sortedlist = null;
        switch (orderBy.toUpperCase()){
            case "AGE":
                sortedlist = sortedMergeByAge(left, right);
                break;
            case "RACE":
                sortedlist = sortedMergeByRace(left, right);
                break;
            default:
                sortedlist = sortedMergeByName(left, right);
                break;
        }
        return sortedlist;
    }

    private node getMiddle(node head)
    {
        if (head == null)
            return head;

        node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void push(Rebelde new_data)
    {
        node new_node = new node(new_data);

        new_node.next = head;
        head = new_node;
    }

    public String toString()
    {
        node headref = this.head;
        StringBuilder sb = new StringBuilder();
        while (headref != null) {
            sb.append(headref.rebel.toString() + "\n");
            headref = headref.next;
        }
        return sb.toString();
    }

}
