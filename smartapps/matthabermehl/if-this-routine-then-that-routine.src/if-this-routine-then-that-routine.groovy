/**
 *  If this routine, then that routine.
 *
 *  Copyright 2015 Matt Habermehl
 *  Version 0.1
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
 *
 */
definition(
    name: "If this routine, then that routine.",
    namespace: "matthabermehl",
    author: "Matt Habermehl",
    description: "One routine triggers another routine (used for combining routines)",
    category: "Convenience",
 	iconUrl: "https://raw.githubusercontent.com/matthabermehl/img/master/ifroutinethenroutine-half.jpg",
    iconX2Url: "https://raw.githubusercontent.com/matthabermehl/img/master/ifroutinethenroutine.jpg",
    iconX3Url: "https://raw.githubusercontent.com/matthabermehl/img/master/ifroutinethenroutine.jpg")


preferences {
	page(name: "getPref")
}
	
def getPref() {    
    dynamicPage(name: "getPref", title: "Choose Routines", install:true, uninstall: true) {

    def phrases = location.helloHome?.getPhrases()*.label
		if (phrases) {
        	phrases.sort()
			section("Select trigger routine...") {
				//log.trace phrases
				input "ifRoutine", "enum", title: "If this routine...", required: true, options: phrases
			}
            section("Select trigger routine...") {
				input "thenRoutine", "enum", title: "...then this routine", required: true, options: phrases
			}
		}
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
    subscribe(location, "routineExecuted", routineChanged)
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
    subscribe(location, "routineExecuted", routineChanged)
}

def routineChanged(evt) {
    if ( evt.displayName == settings.ifRoutine ){
    	location.helloHome?.execute(settings.thenRoutine)
    }
}