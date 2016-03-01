package com.example.zelong.wakeup.Tools;

import android.widget.Toast;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.DeviceList;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.ServiceList;
import org.cybergarage.upnp.ServiceStateTable;
import org.cybergarage.upnp.StateVariable;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.event.EventListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;


/**
 * Created by zelong on 3/1/16.
 */
public class MyCtrlPoint extends ControlPoint implements NotifyListener, DeviceChangeListener, SearchResponseListener, EventListener {
    public MyCtrlPoint() {
        addNotifyListener(this);
        addDeviceChangeListener(this);
        addSearchResponseListener(this);
        addEventListener(this);
        //start();
        search("upnp:rootdevice");
    }

    public void deviceNotifyReceived(SSDPPacket ssdpPacket) {
        String uuid = ssdpPacket.getUSN();
        String target = ssdpPacket.getNT();
        String subType = ssdpPacket.getNTS();
        String location = ssdpPacket.getLocation();
    }
    /*public static void deviceAdded (ControlPoint ctrlPoint) {
        DeviceList rootDevList = ctrlPoint.getDeviceList();
        int nRootDevs = rootDevList.size();
        System.out.println("nRootDevs = " + Integer.toString(nRootDevs));
        for (int n=0; n<nRootDevs; n++) {
            System.out.println(Integer.toString(n) + "fois de boucle");
            Device dev = rootDevList.getDevice(n);
            String devName = dev.getFriendlyName();
            System.out.println("[" + n + "] = " + devName);
        }
    }*/

    public void deviceRemoved(Device dev) {
    }

    @Override
    public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {
        String uuid = ssdpPacket.getUSN();
        String target = ssdpPacket.getST();
        String location = ssdpPacket.getLocation();
    }

    @Override
    public void eventNotifyReceived(String uuid, long seq, String varName, String value) {

    }

    public void openLight() {
        DeviceList rootDevList = this.getDeviceList();
        int nRootDevs = rootDevList.size();
        //System.out.println("nRootDevs = " + Integer.toString(nRootDevs));
        Device light = null;
        Device player = null;
        for (int n = 0; n < nRootDevs; n++) {
            //System.out.println(Integer.toString(n) + "fois de boucle");
            Device d = rootDevList.getDevice(n);
            String devName = d.getFriendlyName();
            System.out.println("[" + n + "] = " + devName);
            if (devName.equals("LIMSI CM11")) {
                light = d;
                Action executeCmd = light.getAction("ExecuteCommand");
                executeCmd.setArgumentValue("ElementName", "Lampe_Bureau1");
                executeCmd.setArgumentValue("Command", "on");
                executeCmd.postControlAction();

            }
        }
    }

    public void closeLight() {
        DeviceList rootDevList = this.getDeviceList();
        int nRootDevs = rootDevList.size();
        //System.out.println("nRootDevs = " + Integer.toString(nRootDevs));
        Device light = null;
        Device player = null;
        for (int n = 0; n < nRootDevs; n++) {
            //System.out.println(Integer.toString(n) + "fois de boucle");
            Device d = rootDevList.getDevice(n);
            String devName = d.getFriendlyName();
            System.out.println("[" + n + "] = " + devName);
            if (devName.equals("LIMSI CM11")) {
                light = d;
                Action executeCmd = light.getAction("ExecuteCommand");
                executeCmd.setArgumentValue("ElementName", "Lampe_Bureau1");
                executeCmd.setArgumentValue("Command", "off");
                executeCmd.postControlAction();

            }
        }
    }

    @Override
    public void deviceAdded(Device dev) {
        DeviceList rootDevList = this.getDeviceList();
        int nRootDevs = rootDevList.size();
        //System.out.println("nRootDevs = " + Integer.toString(nRootDevs));
        Device light = null;
        Device player = null;
        for (int n = 0; n < nRootDevs; n++) {
            System.out.println(Integer.toString(n) + "fois de boucle");
            Device d = rootDevList.getDevice(n);
            String devName = d.getFriendlyName();
            System.out.println("[" + n + "] = " + devName);
            if (devName.equals("LIMSI CM11")) {
                light = d;
                Action executeCmd = light.getAction("ExecuteCommand");
                executeCmd.setArgumentValue("ElementName", "Lampe_Bureau1");
                executeCmd.setArgumentValue("Command", "on");
                executeCmd.postControlAction();

            }
            else if (devName.equals("LIMSI AudioPlayer")) {
                player = d;
                Action executeCmd = player.getAction("ExecuteCommand");
                executeCmd.setArgumentValue("ElementName", "Lecteur_Audio");
                //executeCmd.setArgumentValue("Command", "play");
                executeCmd.setArgumentValue("Command", "play");
                executeCmd.setArgumentValue("setVolume", 20);
                executeCmd.setArgumentValue("setBalance", 0);
                executeCmd.postControlAction();
                Action play = player.getAction("Play");
                play.setArgumentValue("File", "http://srv38.clipconverter.cc/download/4pSXam9knWprZLWr2NmWcLVhnGdqa25xnZmUtHyc0aJ3oqeuy9XXnas%3D/The%20Weeknd%20-%20Earned%20It%20%28Fifty%20Shades%20Of%20Grey%29%20%28Lyric%20Video%29.mp3");
                play.setArgumentValue("Player", "Lecteur_Audio");
                play.postControlAction();
                //Action beep = player.getAction("Beep");
                //beep.setArgumentValue();
            }
        }

        ServiceList serviceList = dev.getServiceList();
        int serviceCnt = serviceList.size();
        for (int n = 0; n < serviceCnt; n++) {
            Service service = serviceList.getService(n);
            ActionList actionList = service.getActionList();
            int actionCnt = actionList.size();
            for (int i = 0; i < actionCnt; i++) {
                Action action = actionList.getAction(i);
                System.out.println("action [" + i + "] = " + action.getName());
            }
            ServiceStateTable stateTable = service.getServiceStateTable();
            int varCnt = stateTable.size();
            for (int i = 0; i < actionCnt; i++) {
                //StateVariable stateVar = stateTable.getServiceStateVariable(i);
                StateVariable stateVar = stateTable.getStateVariable(i);
                System.out.println("stateVar [" + i + "] = " + stateVar.getName());
            }
        }

    }

}
