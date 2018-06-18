
package com.vanluom.group11.quanlytaichinhcanhan.log;

/**
 * Thrown when an exception happens in the release build.
 * Can be caught by the UI components to display the message to the user.
 */

public class ErrorRaisedEvent {
    public ErrorRaisedEvent(String message) {
        this.message = message;
    }

    public String message;
}
