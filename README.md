# CodingChallenge

Description:
You are given sample code that defines a Room database along with a table and a supported operation on that table. Inside you will find a class named SampleMessages.kt that contains some sample messages you should use to populate this db. You are also given the Activity/Fragment/RecyclerView code that is capable of displaying the list of messages. On current run nothing will be shown. The entire code being provided has been written in Kotlin but you are free to add any new classes in java if you want. Feel free to modify the existing code in any way you see fit especially if you find a better way of doing something.
 
 
Requirements:
* Add any code required to populate the DB with the sample messages (SampleMessages.kt) and display the list of messages in MessageListFragment
* A message that is unread should be shown with textStyle=”bold” and a read message should be shown with textStyle=”regular”
* You are expected to add support for swiping a message (see attach reveal_swipes.mp4) in either direction and it show a swipe action behind the cell. You are provided the asset (ic_swipe_markread.png) and the background color of the swipe cell should be #FF0591E0.
* The swipe must complete a threshold of at least 30% the width of the cell before committing. So if the user swiped only 10% and let go it should snap back and the swipe action should be canceled and no action taken (see attach cancel_swipe.mp4)
* If the user passes the 30% threshold and let’s go then the cell should animate all the way in the direction the user was swiping and then animate back to the starting position. (see attach commit_swipe.mp4)
* The only swipe action currently expected is marking a message as read. So if the user performs this swipe action then the message should be marked as read and the title of the subject should no longer be shown as bold. It should be regular (i.e. textStyle=”normal”). This should be shown immediately and should not require the row to be scrolled out of view for the change to take effect. The change should also be persisted to the database so if the app is closed or device rebooted then the changes will be seen.
* Marking a message as read that is already read should do nothing
* Make sure the list can be scrolled normally after you implement swiping on message cells
* Connected tests for UI are not necessary but provide unit tests where applicable