package org.dkf.jed2k.test;

import org.dkf.jed2k.Pair;
import org.dkf.jed2k.ServerConnectionPolicy;
import org.junit.Test;

import java.net.InetSocketAddress;

import static org.junit.Assert.*;

public class ServerConnectionPolicyTest {

    @Test
    public void testServerConenectionPolicyTrivial() {
        ServerConnectionPolicy scp = new ServerConnectionPolicy(1, 2);
        assertNull(scp.getConnectCandidate(1));
        scp.setServerConnectionFailed("123", new InetSocketAddress("192.168.0.9", 1223), 4);
        assertNull(scp.getConnectCandidate(4));
        Pair<String, InetSocketAddress> cc = scp.getConnectCandidate(4 + 1000 + 1);
        assertEquals(Pair.make("123", new InetSocketAddress("192.168.0.9", 1223)), cc);
        scp.setServerConnectionFailed("123", new InetSocketAddress("192.168.0.9", 1223), 0);
        assertNull(scp.getConnectCandidate(1000));
        Pair<String, InetSocketAddress> cc2 = scp.getConnectCandidate(2000 + 1);
        assertEquals(Pair.make("123", new InetSocketAddress("192.168.0.9", 1223)), cc2);
        scp.setServerConnectionFailed("123", new InetSocketAddress("192.168.0.9", 1223), 0);
        assertNull(scp.getConnectCandidate(4000));
        scp.clear();
        assertNull(scp.getConnectCandidate(2999));
        scp.setServerConnectionFailed("123", new InetSocketAddress("192.168.0.9", 1223), 0);
        assertNotNull(scp.getConnectCandidate(1000 + 1));
    }

    @Test
    public void testServerConnectionPolicyMultipleServers() {
        ServerConnectionPolicy scp = new ServerConnectionPolicy(1, 2);
        scp.setServerConnectionFailed("123", new InetSocketAddress("192.168.1.1", 1111), 0);
        assertEquals(Pair.make("123", new InetSocketAddress("192.168.1.1", 1111)), scp.getConnectCandidate(1));
        scp.setServerConnectionFailed("123", new InetSocketAddress("192.168.1.1", 1111), 1);
        assertNull(scp.getConnectCandidate(100));
        scp.setServerConnectionFailed("124", new InetSocketAddress("192.168.1.2", 1111), 100);
        assertEquals(Pair.make("124", new InetSocketAddress("192.168.1.2", 1111)), scp.getConnectCandidate(101));
        scp.setServerConnectionFailed("124", new InetSocketAddress("192.168.1.2", 1111), 101);
        assertNull(scp.getConnectCandidate(1000));
        assertEquals(Pair.make("124", new InetSocketAddress("192.168.1.2", 1111)), scp.getConnectCandidate(1102));
        scp.setServerConnectionFailed("123", new InetSocketAddress("192.168.1.1", 1111), 0);
        assertEquals(Pair.make("123", new InetSocketAddress("192.168.1.1", 1111)), scp.getConnectCandidate(101));
    }
}
