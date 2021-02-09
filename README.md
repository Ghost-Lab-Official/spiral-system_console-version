# spiral-system_console-version

Introduction 

Spot management system is the system that serves people all over the world.  You can ask yourself how? It will be an android, web and USSD application that support  User management including features like registration and login. Not only user management Supported but also spot management with their different categories who can post a spot. On other hand Unregistered clients are able to view published spots by searching according to their need. They can search by image by just uploading a picture,paste it or simply drag, audio search  and text . response returned from search Come as an image,text and video  client choose what to see according to his/her research .
Below is more information of what we must do in each  module.

Modules:
User management module
Simply, we all know that a user is a person who utilizes a system. Our system also, as others, will require the management of users. In user management system we will have: 
User category management as users are categorized into system Admins and end-users.
Create user-category: the system should allow the system Admin to create user categories
Get and filter user-categories
Update user-categories
Delete user-categories

User management  users should be able to login to the system 
users should be able to register for an account
users should be able to reset their passwords 
users should be able to disable their accounts
users should be able to update their account settings
system admin should be able to get all users  of our system 
system admin should be able  to filter system users

Spot management module
The spot can be anything  area, geographical feature, people’s property that can be found in a certain place … It should have the location, services that are available at that spot like hospitality services like hotels,restaurants and others .

If the spot is the people’s property or organization properties that work like services, spot will have the location and available status (available, not available) like emergency cars must have the location and the availability status and other properties

Spot will have the spot type (mountains, hospitals), spot location (country/state, province, district, sector), spot name, spot reviews like available resources such as food available

Spot Review : spot review contains the more infos about the spot , When the information is to be added to the specific spot, we use spot reviews such as videos,audios,text,Images …







Search spot module
		Users will enter a search text to request spots.
		Then the system will fetch all spots relevant to the search text and show them to the user.
The system will have a fallback message that it’ll throw if the searched query is not found.
The system will bring results based on your geographical location each time you search.
The system will be able to show recent searches/history and top searches.
The system will be able to determine WHO questions to differentiate the result set , like : How many (returns number) , What are (returns list)

Search history Module
The system will be able to keep the history of your searches so that it will be able to recommend your recent searches as well as what is related to your searches  just like how youtube does. To mean for this module to work you should first login.
Filter search results module

This is an important module by which the search results will be filtered to get the most relevant results to the user. For better user experience, this is a more important module where the user must be presented with only the most important search results or the search results must be ordered from most relevant to least relevant. The Results will be filtered based on the following criteria:
The location of the user.
The interests of the user by basing on what he/she searches for the most(search history).
Spot categories where the user can specify the most relevant category.
User category. To make the system more accessible, The search results will include the results that might be based on the user category. For example, if the user is a tourist, the results might be somehow based on tourism but it may be also based on something else as specified by the user.
Spot reviews module
The system will be able to handle the reviews on every spot. The user who created the spot will be notified when there is a review made on the spots that he/she made. So that when there is an update the user can be able to add it on the spot or if a user provided some erroneous information provided on the spot, he/she can be able to give correct information on the spot.
