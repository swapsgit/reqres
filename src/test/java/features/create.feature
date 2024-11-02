Feature: create

Scenario Outline: verify create functionality
Given api is responsive we send "<name>" and "<job>" in body
When POST method is used to send request
Then we get response code 201 with body
And we update values using put
And we update values using patch request
And we delete user by using created id

Examples:
|name|job|
|Radhe|agriEngg|
|Shyam|Scientist|