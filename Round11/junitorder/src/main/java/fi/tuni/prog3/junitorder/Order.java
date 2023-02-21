package fi.tuni.prog3.junitorder;

import java.util.*;

public class Order
{
    public static class Item
    {
        private String name;
        private double price;

        public Item(String name, double price) throws IllegalArgumentException {

            if (name == null || price < 0) {
                throw new IllegalArgumentException();
            }
            else
            {
                this.name = name;
                this.price = price;
            }
        }

        public String getName() {
            return this.name;
        }

        public double getPrice() {
            return this.price;
        }

        public String toString() {
            return "Item(" + this.name + ", " + this.price + ")";
        }

        public boolean equals(Order.Item other) {
            if (this.name.equalsIgnoreCase(other.name)) {
                return true;
            }
            return false;
        }
    }

    public static class Entry {

        public Entry(Order.Item item, int count) throws IllegalArgumentException {

        }
        public String getItemName() {
            return null;
        }

        public double getUnitPrice() {
            return 1.0;
        }

        public Order.Item getItem() {
            return null;
        }

        public int getCount() {
            return 1;
        }

        public String toString() {
            return null;
        }
    }

    // Constructs an empty order
    public Order() {
    }

    public boolean addItems(Order.Item item, int count) throws IllegalArgumentException {
        return true;
    }

    public boolean addItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
        return true;
    }

    public List<Order.Entry> getEntries() {
        return null;
    }

    public int getEntryCount() {
        return 1;
    }

    public int getItemCount() {
        return 1;
    }

    public double getTotalPrice() {
        return 1.0;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean removeItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
        return true;
    }
}