# Postman setup
1. Setup environment variables for local:
    ```
    {{host}} : http://localhost:8080
    ```
2. Setup environment variables for dev:
    ```
    {{host}} : http://3.21.12.143:8080
    ```
3. Import collections using EBR.postman_collection.json

# Start project
Open by eclipse and run `src.com.ebr.EBRServer`

# Folder structure
```
--- lib                       // libraries files
--- src
    | --- bean                // models
    | --- db                  // database 
    |     | --- interfaces    // database interfaces
    |     | --- seed          // seed data from json files
    |     |     | --- data    // json data files
    | --- enums               // enums 
    | --- service             // services 
    | --- utils               // utils 
```