package project.flow.data

import project.flow.model.Contact

object ContactData {

    fun getContacts(): List<Contact> {
        return listOf(

            Contact(
                "Arjun Reddy",
                "+91 98480 12345",
                "arjun.reddy@email.com",
                "Hyderabad Tech Solutions",
                "Banjara Hills, Hyderabad, Telangana"
            ),

            Contact(
                "Sneha Iyer",
                "+91 98845 23456",
                "sneha.iyer@email.com",
                "Chennai Design Studio",
                "Adyar, Chennai, Tamil Nadu"
            ),

            Contact(
                "Rahul Nair",
                "+91 97456 34567",
                "rahul.nair@email.com",
                "Kerala Marketing Group",
                "Kakkanad, Kochi, Kerala"
            ),

            Contact(
                "Divya Lakshmi",
                "+91 99001 45678",
                "divya.lakshmi@email.com",
                "Bangalore Healthcare",
                "Indiranagar, Bangalore, Karnataka"
            ),

            Contact(
                "Karthik Varma",
                "+91 98490 56789",
                "karthik.varma@email.com",
                "Vizag Financial Services",
                "MVP Colony, Visakhapatnam, Andhra Pradesh"
            ),

            Contact(
                "Ananya Rao",
                "+91 99123 67890",
                "ananya.rao@email.com",
                "Education First",
                "Gachibowli, Hyderabad, Telangana"
            ),

            Contact(
                "Vikram Gowda",
                "+91 97380 78901",
                "vikram.gowda@email.com",
                "Mysore Construction Ltd",
                "Vijayanagar, Mysuru, Karnataka"
            ),

            Contact(
                "Meera Pillai",
                "+91 98956 89012",
                "meera.pillai@email.com",
                "Kerala Tourism Board",
                "Fort Kochi, Kerala"
            ),

            Contact(
                "Siddharth Raju",
                "+91 98660 90123",
                "siddharth.raju@email.com",
                "Andhra IT Services",
                "Tirupati, Andhra Pradesh"
            ),

            Contact(
                "Pooja Nambiar",
                "+91 97543 01234",
                "pooja.nambiar@email.com",
                "Bangalore Creative Studio",
                "Whitefield, Bangalore, Karnataka"
            )

        )
    }
}