package models;

public class Message {

static final long serialVersionUID = 1L;

private java.lang.String domain;
private java.lang.String event;
private java.lang.String timestamp;
private java.util.Map data;
private java.lang.String topic;

public Message() {
        }

public java.lang.String getDomain() {
        return this.domain;
        }
public java.util.Map getData(){
        return this.data;
        }
public void setData(java.util.Map data){
        this.data = data;
        }
public void setDomain(java.lang.String domain) {
        this.domain = domain;
        }

public java.lang.String getEvent() {
        return this.event;
        }

public void setEvent(java.lang.String event) {
        this.event = event;
        }

public java.lang.String getTimestamp() {
        return this.timestamp;
        }

public void setTimestamp(java.lang.String timestamp) {
        this.timestamp = timestamp;
        }

public Message(java.lang.String domain, java.lang.String event,
        java.lang.String timestamp,java.util.Map data) {
        this.domain = domain;
        this.event = event;
        this.timestamp = timestamp;
        this.data = data;
        }

        public String getTopic() {
                return topic;
        }

        public void setTopic(String topic) {
                this.topic = topic;
        }

        @Override
        public String toString() {
                return super.toString();
        }
}
