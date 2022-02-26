# Lab. Report \#2 – Requirements-Based Test Generation
**SENG 637- Dependability and Reliability of Software Systems**


| Group \#:       | 34 |
|-----------------|---|
| **Student Names:** | Quinn Cooper |
|                 | Mohd Akram Ansari |
|                 | John Matthews Sarno |
|                 | Mahsa Malek |


# 1 Introduction

In a real-world setting, it is rather cumbersome to test coded programs as a whole to make sure that they are working correctly. Automated unit testing allows software to be magnified and checked for functionality in small sections, which tends to be more convenient and reliable. The ultimate goal of conducting such type of test is to make sure that each part of a software works as designed. Hence, it is crucial that students are equipped with fundamental knowledge, such as testing parameters based on their individual requirements. 

In this assignment, JUnit framework was utilized to develop automated test codes and familiarize with mock objects as part of test development.  The activity was divided into three sections: familiarization with Eclipse, reviewing test requirements as specified in Javadocs, and the actual test development stage, where students were able to familiarize themselves with mock objects. 

Testing was mainly focused on the data framework package of JFreeChart, an open-source Java framework for chart calculation, creation, and display. For this assignment, different methods for the following classes were tested: 

1. DataUtilities.java 
2. Range.java

In this regard, two libraries were used in conducting the test: 

1. JUnit4 for Java testing. 
2. JMock for mocking dependencies.

# 2 Detailed description of unit test strategy

Our testing strategy was based on our understanding of the classes in the SUT (JFreeChart) which we obtained from the Javadoc for the SUT. The Javadoc Listed all the methods in the two classes to be tested along with their behaviour description and expected output against different scenarios. 

We used our knowledge of the system from the Javadoc and used black box testing strategy to test the methods (as we did not have access to the raw code of the classes under test). We composed our expected outputs for our tests exclusively from the Javadoc. 

In test cases where the input was numerical, we used Boundary Value Analysis to test multiple input classes instead of randomly selecting input values. 

The following classes were created for every test case which required numerical inputs. A lot of the inputs were unbounded, e.g. calculateColumnTotal method of DataUtilities class. In such cases we tested with positive, negative, null, invalid inputs only. 

1. **Invalid input (Bad combination of numerical Inputs)** 
2. **Negative Value** 
3. **Positive Value** 
4. **Null Value** 

In cases where the input was dependent on some range we used Boundary Value Analysis to divide the input into 4 different classes. 

1. **Upper (More than upper bound)** 
2. **Lower (Less than lower bound)** 
3. **Edge (At the edges of the range)** 
4. **Middle (Middle of the range)** 

However, some of the equivalency classes were skipped during the actual test case definition in order to limit the impact of our testing we used mocking strategy. The DataUtilities Class is dependent on Values2D and KeyedValues classes so we mocked these two classes in our test suite. As instructed, we used JMock for mocking and asserting expectations from the mocked objects.  

Mocking proved to be an excellent way to focus our test to the unit to be tested because: 

- Mocking allows us to execute the unit test without executing the code of the dependencies. This eliminates any errors that might arise due to faults in the dependencies. 
- It allows us to have fine grained control over the behaviour of the mocked dependencies thus allowing us to cover more functionality in the unit being tested. 
- Mocking also helps our tests to be resilient to any changes in the dependent classes. If a dependent class changes and that class is being mocked, unit tests will tell us exactly what class has been changed because only the tests for that class will fail if all dependent classes have been mocked. 

# 3 Test cases developed

The table below extensively lists the test cases that we created for testing the two classes. Each method has been tested by using the strategy detailed in the previous section. 

#### Class: DataUtilities

|                Function | Name                                                         | Equivalency Class |
| ----------------------: | ------------------------------------------------------------ | ----------------- |
|    calculateColumnTotal | test_calculateColumnTotal_AllPositive()                      | Positive          |
|                         | test_calculateColumnTotal_AllNegative()                      | Negative          |
|                         | test_calculateColumnTotal_MixedValues()                      | Mixed             |
|                         | test_calculateColumnTotal_InvalidInput()                     | Invalid           |
|                         | test_calculateColumnTotal_ExceptionThrows_InvalidParameterException() | Null              |
|       calculateRowTotal | test_calculateRowTotalMethod_AllPositive()                   | Positive          |
|                         | test_calculateRowTotalMethod_AllNegative()                   | Negative          |
|                         | test_calculateRowTotalMethod_MixedValues()                   | Mixed             |
|                         | test_calculateRowTotal_InvalidInput()                        | Invalid           |
|                         | test_calculateRowTotal_ExceptionThrows_InvalidParameterException() | Null              |
| getCumulativePercentage | test_getCumulativePercentage_negative()                      | Negative          |
|                         | test_getCumulativePercentage_positive()                      | Positive          |
|                         | test_getCumulativePercentage_null()                          | Null              |
|       createNumberArray | test_createNumberArray_Length()                              | Positive          |
|                         | test_createNumberArray_single()                              | Positive          |
|                         | test_createNumberArray_null()                                | Null              |
|                         | test_createNumberArray()                                     | Positive          |
|     createNumberArray2D | test_createNumberArray2D_null()                              | Null              |
|                         | test_createNumberArray2D_single()                            | Positive          |
|                         | test_createNumberArray2D()                                   | Positive          |

#### Class: Range

| Function                | Name                                                         | Equivalency Class |
| ----------------------: | ------------------------------------------------------------ | ----------------- |
| combine         | test_CombineMethod_PositiveNoOverlap()                       | Positive |
|                 | test_CombineMethod_NegativeNoOverlap()                       | Negative |
|                 | test_CombineMethod_PositivewithOverlap()                     | Positive |
|                 | test_CombineMethod_PositivewithOverlap2()                    | Positive |
|                 | test_CombineMethod_PositivewithOverlap3()                    | Positive |
|                 | test_CombineMethod_PositivewithOverlap4()                    | Positive |
|                 | test_CombineMethod_SimilarRanges()                           | Positive |
|                 | test_CombineMethod_TwoNullRanges()                           | Null     |
|                 | test_CombineMethod_OneNullRange()                            | Null     |
| shift           | test_ShiftMethod_OneNullValue()                              | Null     |
|                 | test_ShiftMethod_PositiveRangeValue()                        | Positive |
|                 | test_ShiftMethod_PositiveRange_NegativeValue()               | Mixed    |
|                 | test_ShiftMethod_PositiveRange_NegativeValue2()              | Mixed    |
|                 | test_ShiftMethod_NegativeRange_NegativeValue()               | Negative |
|                 | test_ShiftMethod_NegativeRange_PositiveValue()               | Positive |
| constraint      | test_ConstrainMethod_PositiveRange_Middle()                  | Middle   |
|                 | test_ConstrainMethod_PositiveRange_UpperBound()              | Upper    |
|                 | test_ConstrainMethod_PositiveRange_LowerBound()              | Lower    |
|                 | test_ConstrainMethod_PositiveRange_EdgeBounds()              | Edge     |
|                 | test_ConstrainMethod_NegativeRange_Middle()                  | Middle   |
|                 | test_ConstrainMethod_NegativeRange_UpperBound()              | Upper    |
|                 | test_ConstrainMethod_NegativeRange_LowerBound()              | Lower    |
|                 | test_ConstrainMethod_NegativeRange_EdgeBounds()              | Edge     |
| contains        | test_ContainsMethod_PositiveRange_LowerBound()               | Lower    |
|                 | test_ContainsMethod_PositiveRange_UpperBound()               | Upper    |
|                 | test_ContainsMethod_PositiveRange_Middle()                   | Middle   |
|                 | test_ContainsMethod_InvalidValue_PositiveRange_LowerBound()  | Lower    |
|                 | test_ContainsMethod_InvalidValue_PositiveRange_UpperBound()  | Upper    |
|                 | test_ContainsMethod_NegativeRange_LowerBound()               | Lower    |
|                 | test_ContainsMethod_NegativeRange_UpperBound()               | Upper    |
|                 | test_ContainsMethod_NegativeRange_Middle()                   | Middle   |
|                 | test_ContainsMethod_InvalidValue_NegativeRange_LowerBound()  | Lower    |
|                 | test_ContainsMethod_InvalidValue_NegativeRange_UpperBound()  | Upper    |
| equals          | test_EqualsMethod_PositiveRange_SimilarValues()              | Positive |
|                 | test_EqualsMethod_PositiveRange_DifferentValues()            | Positive |
|                 | test_EqualsMethod_OneNullRange_OneActualRange()              | Null     |
|                 | test_EqualsMethod_NegativeRange_SimilarValues()              | Negative |
|                 | test_EqualsMethod_NegativeRange_DifferentValues()            | Negative |
|                 | test_EqualsMethod_PositiveandNegativeRange_DifferentValues() | Mixed    |
| expand          | test_expand_ErrorRange()                                     | Invalid  |
|                 | test_expand_NullRange()                                      | Null     |
|                 | test_expand_example()                                        | Positive |
|                 | test_expandToInclude_example()                               | Positive |
|                 | test_expandToInclude_null()                                  | Null     |
| getCentralValue | test_getCentralValue_example()                               | Positive |
| getLength       | test_getLength_example()                                     | Positive |
|                 | test_getLength_voidRange()                                   | Null     |
| toString        | test_toString_example()                                      | Positive |
|                 | test_toString_voidRange()                                    | Null     |
| shift           | test_ShiftException()                                        | Null     |
|                 | test_Shift()                                                 | Negative |
| lowerBound      | test_LowerBound()                                            | Negative |
| upperBound      | test_UpperBound()                                            | Positive |
| intersects      | test_Intersects()                                            | Negative |

# 4 How the team work/effort was divided and managed

All the testing was done in phases and in a collaborative manner. We also made sure that the quantity and the type of workload divided was equal among all team members since this was a more manageable and quantifiable assignment with more discrete tasks. This gave equal learning opportunity to each team member as well as keeping the workload to a minimum. We heavily relied on GitHub for collaboration. 

The first phase was requirement understanding. We went over the assignment description and understood the tasks. Then we collectively went over the Javadoc for the SUT so that all of us have a clear understanding of the test cases we had to write. 

The second phase was test case writing. In this case we worked asynchronously by first dividing the functions from the two classes into 4 parts (for four team members). We then completed the test cases assigned to each of us separately. 

The third stage was testcase cleanup and verification. We then got together and went over all the test cases and fixing any issues and reviewing each other's work at the same time. This helped us to eliminate several errors in our code. We prepared a report of all test cases in an excel sheet. 

The final stage was document preparation. We again worked together in a group call and made a document detailing our test plan and the test cases and finalizing the report for submission. 


# 5 Difficulties encountered, challenges overcome, and lessons learned

One large difficulty that we encountered was that when we thought we were finished we had used the wrong version of JUnit to prepare our test cases. This had an added benefit as we quickly learned the differences between JUnit5 and the JUnit used in this assignment.   

Another was dealing with the sheer number of tests to be run, even with the limited scope of the assignment we found ourselves overwhelmed with how many tests there were. 


# 6 Comments/feedback on the lab itself

We found Junit and mocking to be pretty interesting. This assignment helped get hands on experience with JUnit. Although there was a bit of a learning curve with the libraries that we were suggested to use but It gave us practical exposure to the unit testing frameworks.

