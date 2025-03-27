package edu.cdivtc.entity;
public class Express {
        private int id; // 快递id
        private String sendName; // 寄件人姓名
        private String sendPhone; // 寄件人手机号码
        private String sendCompany; // 寄件人单位
        private String sendAddress; // 寄件人地址
        private String sendPostcode; // 寄件人邮编
        private String receiveName; // 收件人姓名
        private String receivePhone; // 收件人手机号码
        private String receiveCompany; // 收件人单位
        private String receiveAddress; // 收件人地址
        private String receivePostcode; // 收件人邮编
        // 无参构造
        public Express() {
        }
        // 有参构造
        public Express(int id, String sendName, String sendPhone, String sendCompany, String sendAddress, String sendPostcode, String receiveName, String receivePhone, String receiveCompany, String receiveAddress, String receivePostcode) {
            this.id = id;
            this.sendName = sendName;
            this.sendPhone = sendPhone;
            this.sendCompany = sendCompany;
            this.sendAddress = sendAddress;
            this.sendPostcode = sendPostcode;
            this.receiveName = receiveName;
            this.receivePhone = receivePhone;
            this.receiveCompany = receiveCompany;
            this.receiveAddress = receiveAddress;
            this.receivePostcode = receivePostcode;
        }
        // getter和setter方法
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getSendName() {
            return sendName;
        }
        public void setSendName(String sendName) {
            this.sendName = sendName;
        }
        public String getSendPhone() {
            return sendPhone;
        }
        public void setSendPhone(String sendPhone) {
            this.sendPhone = sendPhone;
        }
        public String getSendCompany() {
            return sendCompany;
        }
        public void setSendCompany(String sendCompany) {
            this.sendCompany = sendCompany;
        }
        public String getSendAddress() {
            return sendAddress;
        }
        public void setSendAddress(String sendAddress) {
            this.sendAddress = sendAddress;
        }
        public String getSendPostcode() {
            return sendPostcode;
        }
        public void setSendPostcode(String sendPostcode) {
            this.sendPostcode = sendPostcode;
        }
        public String getReceiveName() {
            return receiveName;
        }
        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }
        public String getReceivePhone() {
            return receivePhone;
        }
        public void setReceivePhone(String receivePhone) {
            this.receivePhone = receivePhone;
        }
        public String getReceiveCompany() {
            return receiveCompany;
        }
        public void setReceiveCompany(String receiveCompany) {
            this.receiveCompany = receiveCompany;
        }
        public String getReceiveAddress() {
            return receiveAddress;
        }
        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
        }
        public String getReceivePostcode() {
            return receivePostcode;
        }
        public void setReceivePostcode(String receivePostcode) {
            this.receivePostcode = receivePostcode;
        }
        // toString方法
        @Override
        public String toString() {
            return "Express{" +
                    "id=" + id +
                    ", sendName='" + sendName + '\'' +
                    ", sendPhone='" + sendPhone + '\'' +
                    ", sendCompany='" + sendCompany + '\'' +
                    ", sendAddress='" + sendAddress + '\'' +
                    ", sendPostcode='" + sendPostcode + '\'' +
                    ", receiveName='" + receiveName + '\'' +
                    ", receivePhone='" + receivePhone + '\'' +
                    ", receiveCompany='" + receiveCompany + '\'' +
                    ", receiveAddress='" + receiveAddress + '\'' +
                    ", receivePostcode='" + receivePostcode + '\'' +
                    '}';
        }
    }

