# COMP3005 BookstoreGUI Project

The BookstoreGUI Project involves creating a graphical user interface for a bookstore with which administrators and clients can interact. It was build using Java using a JDBC driver to communicate with a PostgreSQL database.

## Installation

Currently, it is recommended that this code be downloaded and opened with your IDE. This is because Maven has been difficult about including the PostgreSQL JDBC Driver dependency. The code can be downloaded by cloning the project or by downloading the provided jarfile. The provided jarfile includes both the source code and the corresponding compiled versions of the source code. The jar file manifest is correct as well, the issue is only with the JDBC driver dependency.

In addition you will need a recent version of PostgreSQL installed. The SQL folder includes the DDL and inserts that are needed to create the database.

## Usage

Update the constants in BookstoreModel to match the URL, user id and password of your database. Run the main method in BookstoreClient in your IDE with the JDBC dependency included and the provided sql files already run in the PostgresSQL query tool. The GUI should otherwise be quite easy to follow.

## History

Version 1 (2021-12-19) - Created

## Issues

At this point in time the functionalities for administrators to view reports and for customers to track their orders are not fully implemented. The GUI portions of these functionalities are implemented but the actual querying of the database has not been completed yet.

## Credits

Lead Developer - Evan Smedley

## License

The MIT License (MIT)

Copyright (c) 2021 Evan Smedley

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
