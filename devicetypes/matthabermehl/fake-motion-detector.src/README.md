You may find this (sort of hacky) method for using your D-Link DCS-932L as a motion sensor useful: [IFTTT](https://ifttt.com/)  has the ability to trigger a SmartThings event when it receives an email. Of course, it won't trigger a motion detection but it *will* trigger a switch - even a simulated switch. 

All that is needed to turn that switch into a motion detector is 1) an app that activates a motion detector to broadcast activation when the switch is flipped (and flips the switch back automatically), and 2) a custom device type that masquerades as a motion detector that will actually accept commands (to turn its activation states on and off).

I whipped up both of these tonight and it works ok for most purposes.
App: [https://goo.gl/aYVk8l](https://goo.gl/aYVk8l)
Device Type: [https://goo.gl/rQEhtO](https://goo.gl/rQEhtO)

**To install the device:**

* Go to "My Device Types" and create a new device from code. Past the Device Type code (link above) into the code window.
* Save the device type and publish it.
* Go to the IDE and under "My Devices" create a new device.
* The Name and Network ID can be whatever you like.
* Under "Type", scroll the list all the way to the bottom and select the device type you just added.
* Select your location and hub and click "Create"

**To Install the App:**

* In the IDE go to "My SmartApps" and create a new SmartApp from code. Paste in the app code from the link above.
* Save the app and publish it.

**To Hook it All Up:**

* Add a Simulated Switch under "My Devices"  through the SmartThings IDE in a way similar to how you created the device above.
* Create an IFTTT recipe that takes an email and triggers the Simulated Switch you just added.
* Install the SmartApp from your mobile device in the SmartThings app.
* During installation, select your virtual switch as the first item and your fake motion sensor as the second item.
* The delay setting allows you to specify how many seconds the fake motion sensor stays active for prior to timing out. You'll want to synchronize this with the settings you applied to the back end (web interface) of your DCS-932L: For example, if you have the camera set to send an email on motion detection every 30 seconds, you'll want to have a delay of more than 30 seconds between the fake motion detector trigger and its timeout. This way if the camera continually senses motion, your fake motion sensor has a good chance of being continually on (though no guarantees for timing because of the nature of email and the fact that the detection signal is being lopped through IFTTT). If motion stops, it will take x seconds for the motion detector to signal that there is no motion, where x is the delay for timeout that you set when installing the app.

Note that when you set up your DCS-932L to send emails on motion detection, you need to specify an SMTP server. You can use gmail's smpt.gmail.com but you have to use an app-specific password.

This is my first real attempt at coding something interesting in this ecosystem, so forks and improvements are very much welcome.

Thanks!
