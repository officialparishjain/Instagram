# Project Name
Instagram Application

# Frameworks and Language Used
**Spring Boot** 2.1.0
**Java** 20.0
**Maven** 3.8.1

# Data Flow
The following functions are used in the data flow of this project:

# Mappings

**InstaUserController:**

POST /insta/signup: Sign up a user to Instagram.
GET /insta/signin: Sign in a user to Instagram.
DELETE /insta/signout/{email}: Sign out a user from Instagram.
POST /insta/follow/{email}/{token}/{otherId}: Follow a user.
POST /insta/like/{email}/{token}: Like a post.


**InstaAdminController:**

POST /admin/add: Register an admin.
PUT /admin/user/{id}/{blueTick}: Toggle blue tick for a user.

**InstaPostController:**

POST /post/add: Add a new post.
GET /post/posts/all/{email}/{token}: Get all posts.


## Models


InstaUser:

Attributes: userFirstName, userLastName, userDob, userEmail, userPhoneNumber, userPassword, username, userBio.
Represents a user in the Instagram application.
InstaPostLike:

Attributes: likeId, instaPost, instaUser.
Represents a like given by a user on a post in Instagram.
InstaPost:

Attributes: postId, postDate, postData, postCaption, instaUser.
Represents a post made by a user in Instagram.
InstaFollowing:

Attributes: followingTableId, user, following.
Represents a following relationship between two users in Instagram.
InstaFollower:

Attributes: followerTableId, user, follower.
Represents a follower relationship between two users in Instagram.
InstaAdmin:

Attributes: adminId, adminFirstName, adminLastName, adminEmail.
Represents an administrator in the Instagram application.
AuthenticationToken:

Attributes: authTokenId, authToken, authTokenDate, authInstaUser.
Represents an authentication token associated with a user in Instagram..


## Controller


The InstaPostController class handles HTTP requests related to Instagram-like posts. It includes methods for creating a new post and retrieving all posts. The createPost() method creates a new post by calling the InstaPostService and returns the created post in the HTTP response. The getAllPost() method retrieves all posts for a given user email and authentication token by calling the InstaPostService and AuthenticationService and returns the list of posts in the HTTP response.

The InstaUserController class handles HTTP requests related to Instagram-like users. It includes methods for user signup, user sign-in, and updating user details. The signup() method creates a new user by calling the InstaUserService and returns the user details in the HTTP response. The signin() method authenticates the user by calling the InstaUserService and returns the authentication token in the HTTP response. The updateUserMobileByEmail() method updates the user's mobile number for a given user email and authentication token by calling the InstaUserService and AuthenticationService and returns the HTTP response indicating whether the update was successful.


## Services

The InstaUser class contains attributes such as first name, last name, age, email, phone number, and password, which are used to register and authenticate users. The AuthenticationToken class represents a user token that is generated upon successful authentication. The AuthenticationService class provides methods to save and retrieve user tokens from the database.

The InstaPostService class is used to manage posts made by users. It contains methods to create and retrieve user posts. The createPost method is used to create a new post, and the fetchAllPost method is used to retrieve all posts by a particular user.

The InstaUserService class provides methods for user registration and authentication. The signUpUser method is used to register a new user, while the signInUser method is used to authenticate a user. The generateEncryptedPassword method is used to encrypt user passwords using the MD5 algorithm. Additionally, the getInstaUserById method is used to retrieve a user by their ID, and the findInstaUserByUserEmail method is used to retrieve a user by their email address.

_**Repository:**_ The repository layer is responsible for interacting with the database. It uses Spring Data JPA to perform CRUD (create, read, update, delete) operations on entities.


# Database Structure Used
I have used MySql as Database.

# Project Summary

This is an ecommerce application that allow user to place order and having differnt fetures also



