**Data Query API**
----
### **Setup:**
   
   **I created this project with Docker (version 2.3.0.4 (46911)), so the easiest way to run it is to install Docker, launch Terminal, `cd` to the root directory of this project and run the following commands:**
  
  1. `docker-compose build` 
  2. `docker-compose up` 
  
  **The application runs on localhost:9000.**
  
### Methods
  
  `GET` | `POST`
  
### URL

  **The URLs are structured as follows:**
  
  `GET /store?query=...`
  
  `POST /store`
  
  `{
   "id": "first-post", "title": "My First Post", "content": "Hello World!", "views": 1,
   "timestamp": 1555832341
   }`

### URL Params

   **Queries made to this API support the following operators, some of which can be combined:**
    
  `EQUAL(property,value)`
    
  `AND(a,b)`
    
  `OR(a,b)`
    
  `NOT(a)`
    
  `LESS_THAN(property,value)`

   **Examples:**
   
   `AND(EQUAL(id,"first-post"),EQUAL(views,100))`
   
   `NOT(EQUAL(id,"first-post"))`

   `LESS_THAN(views,100)`
   
   ***NB! Operators `GREATER_THAN` and `LESS_THAN` only work when an integer is passed as a value.**

### Success Responses:
  
  **A successful `GET` request returns the following response:**

  * **Code:** 200 OK<br />
    **Content:** `[{
                  "id": "abc",
                  "title": "Alphabet", "content": "ABCDE...", "views": 1,
                  "timestamp": 1555832341
                  }]`
                  
  **A successful `POST` request returns the following response:**
    
  * **Code:** 200 OK<br />
    **Content:** `{}`
 
### Error Responses:

  **When an invalid argument is passed in the `POST` request (see note in **URL Params**), the following error response is returned:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{
                      "code": "INVALID_ARGUMENT",
                      "message": "Failed to process a non-number value with a comparison operator, argument: id,\"first\""
                  }`

### Testing:

   **To run tests, run the following command in the root directory of the project:**

   `sbt test`

### Sample Calls:

   `curl --header "Content-Type: application/json" --request POST --data '{"id":"first-post","title":"Alphabet","content":"ABCDE...","views":1,"timestamp":1555832341}' http://localhost:9000/store`

   `curl --request GET http://localhost:9000/store`  
    
   `curl --request GET 'http://localhost:9000/store?query=EQUAL(id,"first-post")'`
    
   `curl --request GET 'http://localhost:9000/store?query=EQUAL(views,100)'`
    
   `curl --request GET 'http://localhost:9000/store?query=AND(EQUAL(id,"first-post"),EQUAL(views,100))'`
    
   `curl --request GET 'http://localhost:9000/store?query=OR(EQUAL(id,"first-post"),EQUAL(id,"second-post"))'`
 
   `curl --request GET 'http://localhost:9000/store?query=NOT(EQUAL(id,"first-post"))'`

   `curl --request GET 'http://localhost:9000/store?query=GREATER_THAN(views,100)'`
   
   `curl --request GET 'http://localhost:9000/store?query=LESS_THAN(views,100)'`