# NoteTakingApp
Have you ever been taking list of things you had to buy from market on the papers? if soo,this app is for you with easy interface and easy to use,easy to understand the code and modify it for yourself.
/////////////INSIDE MAINACTIVITY///////////////////
we get the 'notes' set from SharedPreferences which we had setted in EditActivity,we put null the default,so if do not have anything
inside the SharedPreferences (its empty and we do not have any note) in that case we add an example note to our list,otherwise we 
add the set to an ArrayList and set the adapter and everything else needed to display it to the ListView,and from here user can LongClick,Edit etc
everything else is pretty much simple.
