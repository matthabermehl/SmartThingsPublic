/**
 *  Fake Motion Detector
 *
 *  Copyright 2015 Matt Habermehl
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 * 	A simple device type that is meant to take commands to broadcast motion or motion stopping.
 *	Obvious use is to trigger SmartApps that use motion detection from another app.
 */
metadata {
	definition (name: "Fake Motion Detector", namespace: "matthabermehl", author: "Matt Habermehl") {
		capability "Motion Sensor"
        capability "Actuator"
        capability "Sensor"

		command "activate"
		command "deactivate"
	}

    // UI tile definitions
    tiles {
        standardTile("motion", "device.motion", width: 2, height: 2) {
            state("active", label:'motion', icon:"st.motion.motion.active", backgroundColor:"#53a7c0")
            state("inactive", label:'no motion', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff")
        }

        main "motion"
        details "motion"
    }
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing ${description}"
}

// handle commands
def activate() {
	log.debug "Executing 'activate'"
    sendEvent(
		name: "motion",
		value: "active",
		descriptionText: "Motion simulated"
	)
}

def deactivate() {
	log.debug "Executing 'deactivate'"
	sendEvent(
		name: "motion",
		value: "inactive",
		descriptionText: "Motion stop simulated"
	)
}


