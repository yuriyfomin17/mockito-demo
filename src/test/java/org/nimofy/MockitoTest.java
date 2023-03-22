package org.nimofy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    //
    @InjectMocks
    Main main;


    @Test
    public void testListSize() {
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
    @Test
    public void testStabbing() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");

        System.out.println(mockedList.get(0));

        System.out.println(mockedList.get(999));

    }
//
    @Test
    public void testVerifyInvocations() {
        LinkedList mockedList = mock(LinkedList.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()

        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }
//
//    @Test
//    public void stubbingVoidMethodsWithExceptions() {
//
//        LinkedList mockedList = mock(LinkedList.class);
//        doThrow(new RuntimeException()).when(mockedList).clear();
//        mockedList.clear();
//    }
//
    @Test
    public void testVerificationInOrder() {
        LinkedList mockedList = mock(LinkedList.class);
        mockedList.add("was added first");
        mockedList.add("was added second");

        InOrder inOrder = inOrder(mockedList);
        inOrder.verify(mockedList).add("was added first");
        inOrder.verify(mockedList).add("was added second");
    }
//
    @Test
    public void testMainMethod() {
        System.out.println(this.main.testMethod());
    }
}
