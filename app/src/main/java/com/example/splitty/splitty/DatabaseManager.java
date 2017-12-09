package com.example.splitty.splitty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.splitty.splitty.Classes.Contact;
import com.example.splitty.splitty.Classes.ContactGroup;
import com.example.splitty.splitty.Classes.Event;
import com.example.splitty.splitty.Classes.Purchase;
import com.example.splitty.splitty.Classes.PurchaseGroup;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "splittyDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContact = "create table CONTACT (C_ID integer primary key autoincrement, " +
                "C_FIRST text, C_LAST text, C_EMAIL text)";
        String createEvent = "create table EVENT (E_ID number primary key, E_NAME text)";
        String createPurchase = "create table PURCHASE (P_ID integer primary key autoincrement, " +
                "P_DESC text, P_BUYER_ID number, P_COST number, P_DATE date)";
        String createContactGroup = "create table CONTACT_GROUP (C_GROUP_ID integer primary key autoincrement," +
                " C_ID number, E_ID)";
        String createPurchaseGroup = "create table PURCHASE_GROUP (P_GROUP_ID integer primary key autoincrement," +
                "P_ID number, E_ID number)";

        db.execSQL(createContact);
        db.execSQL(createEvent);
        db.execSQL(createPurchase);
        db.execSQL(createContactGroup);
        db.execSQL(createPurchaseGroup);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists CONTACT");
        db.execSQL("drop table if exists EVENT");
        db.execSQL("drop table if exists PURCHASE");
        db.execSQL("drop table if exists CONTACT_GROUP");
        db.execSQL("drop table if exists PURCHASE_GROUP");
        onCreate(db);
    }

    public void reload() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists CONTACT");
        db.execSQL("drop table if exists EVENT");
        db.execSQL("drop table if exists PURCHASE");
        db.execSQL("drop table if exists CONTACT_GROUP");
        db.execSQL("drop table if exists PURCHASE_GROUP");
        onCreate(db);
    }

    public void insertContact(Contact c) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into CONTACT values (null, '" + c.getFirstName() + "', '" + c.getLastName() + "', '" + c.getEmail() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertEvent(Event e) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into EVENT values(" + e.getId() + ", '" + e.getName() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertPurchase(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into PURCHASE values(null, '" + p.getDesc() + "', " +
                p.getBuyerId() + ", " + p.getCost() + ")";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertContactGroup(ContactGroup cg) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into CONTACT_GROUP values(null, " + cg.getContactId() + ", " +
                cg.getEventId() + ")";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertPurchaseGroup(PurchaseGroup pg) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into PURCHASE_GROUP values(null, " + pg.getPurchaseId() + ", " +
                pg.getEventId() + ")";

        db.execSQL(sqlInsert);
        db.close();
    }

    public Contact selectContactById(int contactId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Contact contact = new Contact();

        String sqlQuery = "select * from CONTACT where C_ID = " + contactId;
        Cursor curs = db.rawQuery(sqlQuery, null);

        while (curs.moveToNext()) {
            contact = new Contact();
            contact.setId(curs.getInt(0));
            contact.setFirstName(curs.getString(1));
            contact.setLastName(curs.getString(2));
            contact.setEmail(curs.getString(3));
        }
        curs.close();

        return contact;
    }

    public ArrayList<Contact> selectContactByName(String c_first) {
        ArrayList<Contact> query = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor curs = db.rawQuery("SELECT * FROM CONTACT WHERE C_FIRST like '%" +
                c_first + "%'", null);
        while (curs.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(curs.getInt(0));
            contact.setFirstName(curs.getString(1));
            contact.setLastName(curs.getString(2));
            contact.setEmail(curs.getString(3));
            query.add(contact);
        }
        curs.close();
        return query;
    }

    public ArrayList<Contact> selectAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Contact> contacts = new ArrayList<>();
        String sqlQuery = "select * from CONTACT";

        Cursor curs = db.rawQuery(sqlQuery, null);

        while (curs.moveToNext()) {
            int id = curs.getInt(0);
            String firstName = curs.getString(1);
            String lastName = curs.getString(2);
            String email = curs.getString(3);

            contacts.add(new Contact(id, firstName, lastName, email));
        }
        curs.close();
        return contacts;
    }

    public Event selectEventById(int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Event e = null;

        try {
            String sqlQuery = "select * from EVENT where E_ID = " + eventId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                String name = curs.getString(1);

                e = new Event(id, name);
            }
            curs.close();
        } catch (Exception ex) {
            Log.wtf("selectEventById error", ex.getMessage());
        }

        return e;
    }

    public ArrayList<Event> selectAllEvents() {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Event> events = new ArrayList<>();
        String sqlQuery = "select * from EVENT";

        Cursor curs = db.rawQuery(sqlQuery, null);

        while (curs.moveToNext()) {
            int id = curs.getInt(0);
            String name = curs.getString(1);

            events.add(new Event(id, name));
        }
        curs.close();
        return events;
    }

    public Purchase selectPurchaseById(int purchaseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Purchase p = null;

        try {
            String sqlQuery = "select * from PURCHASE where P_ID = " + purchaseId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                String desc = curs.getString(1);
                int buyerId = curs.getInt(2);
                double cost = curs.getDouble(3);
                Date date = new Date(curs.getLong(4) * 1000);

                p = new Purchase(id, desc, buyerId, cost);
            }
            curs.close();
        } catch (Exception ex) {
            Log.wtf("selectPurchaseById error", ex.getMessage());
        }

        return p;
    }

    public ArrayList<Purchase> selectAllPurchases() {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Purchase> purchases = new ArrayList<>();
        String sqlQuery = "select * from EVENT";

        Cursor curs = db.rawQuery(sqlQuery, null);

        while (curs.moveToNext()) {
            int id = curs.getInt(0);
            String desc = curs.getString(1);
            int buyerId = curs.getInt(2);
            double cost = curs.getDouble(3);
            Date date = new Date(curs.getLong(4) * 1000);

            purchases.add(new Purchase(id, desc, buyerId, cost));
        }

        return purchases;
    }

    public ContactGroup selectContactGroupById(int contactGroupId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContactGroup cg = null;

        try {
            String sqlQuery = "select * from CONTACT_GROUP where C_GROUP_ID = " + contactGroupId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                int contactId = curs.getInt(1);
                int eventId = curs.getInt(2);

                cg = new ContactGroup(id, contactId, eventId);
            }
            curs.close();
        } catch (Exception ex) {
            Log.wtf("selectContactGroupById error", ex.getMessage());
        }

        return cg;
    }

    public PurchaseGroup selectPurchaseGroupById(int purchaseGroupId) {
        SQLiteDatabase db = this.getWritableDatabase();
        PurchaseGroup pg = null;

        try {
            String sqlQuery = "select * from PURCHASE_GROUP where P_GROUP_ID = " + purchaseGroupId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                int numPurchase = curs.getInt(1);
                double totalCost = curs.getDouble(2);

                pg = new PurchaseGroup(id, numPurchase, totalCost);
            }
            curs.close();
        } catch (Exception ex) {
            Log.wtf("selectContactGroupById error", ex.getMessage());
        }

        return pg;
    }

    public void updateContactById(int contactId, String firstName, String lastName,
                                  String email, double loaned, double owed) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();
        cv.put("C_FIRST", firstName);
        cv.put("C_LAST", lastName);
        cv.put("C_EMAIL", email);

        db.update("CONTACT", cv, "C_ID = " + contactId, null);
    }

    public void updateEventById(int eventId, String name, int contactGroupId, int purchaseGroupId,
                                Date startDate, Date endDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("E_NAME", name);
        cv.put("C_GROUP_ID", contactGroupId);
        cv.put("P_GROUP_ID", purchaseGroupId);
        cv.put("E_START_DATE", String.valueOf(startDate));
        cv.put("E_END_DATE", String.valueOf(endDate));

        db.update("EVENT", cv, "E_ID = " + eventId, null);
    }

    public void updatePurchaseById(int purchaseId, String desc, int buyerId, double cost,
                                   Date date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("P_DESC", desc);
        cv.put("P_BUYER_ID", buyerId);
        cv.put("P_COST", cost);
        cv.put("P_DATE", String.valueOf(date));

        db.update("PURCHASE", cv, "P_ID = " + purchaseId, null);
    }

    public void updateContactGroupById(int contactGroupId, int contactId, int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("C_ID", contactId);
        cv.put("E_ID", eventId);

        db.update("CONTACT_GROUP", cv, "C_GROUP_ID = " + contactGroupId,
                null);
    }

    public void updatePurchaseGroupById(int purchaseGroupId, int purchaseId, double eventId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("P_ID", purchaseId);
        cv.put("E_ID", eventId);

        db.update("PURCHASE_GROUP", cv, "P_GROUP_ID = " + purchaseGroupId,
                null);
    }
}