Feature: Register facility

Scenario Outline: verify functionality of register feature
Given API page is responsive and "<username>" and "<email>" and "<password>" in body
When we use POST method to send request
Then we get id with token in response

Examples:
|username|email|password|
|user1|test1@gmail.com|pass@123|
|user2|eve.holt@reqres.in|pistol|
