package com.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import com.vev.calculator.Calculator;

public class CalculatorTests {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Teste básico - dois números")
    void testBasicAddition() {
        assertEquals(76, calculator.evaluate("54+22"));
        assertEquals(5, calculator.evaluate("2+3"));
        assertEquals(100, calculator.evaluate("50+50"));
    }
    
    @Test
    @DisplayName("Teste com múltiplos números - exemplo dado")
    void testMultipleNumbers() {
        assertEquals(219, calculator.evaluate("54+22+32+4+105+2"));
    }
    
    @Test
    @DisplayName("Teste com vários números diferentes")
    void testVariousNumbers() {
        assertEquals(15, calculator.evaluate("1+2+3+4+5"));
        assertEquals(30, calculator.evaluate("10+10+10"));
        assertEquals(1000, calculator.evaluate("100+200+300+400"));
        assertEquals(6, calculator.evaluate("1+1+1+1+1+1"));
    }
    
    @Test
    @DisplayName("Teste com um único número")
    void testSingleNumber() {
        assertEquals(42, calculator.evaluate("42"));
        assertEquals(1, calculator.evaluate("1"));
        assertEquals(999, calculator.evaluate("999"));
    }
    
    @Test
    @DisplayName("Teste com zero")
    void testWithZero() {
        assertEquals(0, calculator.evaluate("0"));
        assertEquals(10, calculator.evaluate("0+10"));
        assertEquals(10, calculator.evaluate("10+0"));
        assertEquals(5, calculator.evaluate("0+2+3"));
        assertEquals(0, calculator.evaluate("0+0+0"));
    }
    
    @Test
    @DisplayName("Teste com números grandes")
    void testLargeNumbers() {
        assertEquals(2000000, calculator.evaluate("1000000+1000000"));
        assertEquals(1234567, calculator.evaluate("1000000+200000+30000+4000+500+60+7"));
    }
    
    @Test
    @DisplayName("Teste com espaços - causam erro na implementação atual")
    void testSpacesError() {
        // A implementação atual não trata espaços, causando NumberFormatException
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5 + 5"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate(" 5+5 "));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10 + 20"));
    }
    
    @Test
    @DisplayName("Teste sem espaços")
    void testNoSpaces() {
        assertEquals(15, calculator.evaluate("5+10"));
        assertEquals(30, calculator.evaluate("10+10+10"));
        assertEquals(6, calculator.evaluate("1+2+3"));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - string vazia")
    void testEmptyString() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate(""));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - string nula")
    void testNullString() {
        assertThrows(NullPointerException.class, () -> calculator.evaluate(null));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - caracteres não numéricos")
    void testInvalidCharacters() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("a + b"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10 + abc"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("xyz + 20"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5 + 3.5"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("@#$ + 10"));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - números negativos")
    void testNegativeNumbers() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("-5 + 10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10 + -5"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("-1 + -2"));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - operadores incorretos")
    void testIncorrectOperators() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5 - 3"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10 * 2"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("8 / 2"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5 & 3"));
    }
    
    @Test
    @DisplayName("Teste de comportamento específico - bug na implementação")
    void testImplementationBug() {
        // A implementação atual tem um bug: falta de chaves no for loop
        // Isso causa comportamento inesperado com strings terminadas em "+"
        
        // Comportamento inesperado: deveria gerar exceção, mas retorna apenas o primeiro número
        assertEquals(5, calculator.evaluate("5+")); // Bug: só processa o primeiro número
        assertEquals(0, calculator.evaluate("+")); // Bug: só processa string vazia que vira 0
        
        // Comportamento correto para casos válidos
        assertEquals(15, calculator.evaluate("5+10"));
        assertEquals(30, calculator.evaluate("10+20"));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - strings mistas")
    void testMixedInvalidStrings() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5 + hello + 3"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10 + 20 + error"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("abc + 123 + def"));
    }
    
    @Test
    @DisplayName("Teste de casos extremos - números muito grandes")
    void testOverflow() {
        // Testando próximo ao limite do Integer.MAX_VALUE
        int maxInt = Integer.MAX_VALUE;
        String largeNumber = String.valueOf(maxInt);
        assertEquals(maxInt, calculator.evaluate(largeNumber));
        
        // Este teste pode causar overflow dependendo da implementação
        assertThrows(NumberFormatException.class, () -> 
            calculator.evaluate("999999999999999999999999999999"));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - caracteres especiais")
    void testSpecialCharacters() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+@"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("#10+20"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10+$5"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5%+10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10+5!"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("&^%+123"));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - números decimais")
    void testDecimalNumbers() {
        // A implementação só aceita inteiros
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5.5+10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("10+3.14"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("1.1+2.2+3.3"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("0.5+0.5"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("100.0"));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - notação científica")
    void testScientificNotation() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("1e5+2"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+1E3"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("2.5e2+100"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("1.0E+6"));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - formatos numéricos incorretos")
    void testIncorrectNumericFormats() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5,000+1000")); // vírgula como separador
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("1.000+2.000")); // ponto como separador de milhares
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("0x10+5")); // hexadecimal
        // Nota: "010+5" funciona pois Integer.valueOf("010") = 10 (não interpreta como octal)
        assertEquals(15, calculator.evaluate("010+5")); // comportamento real da implementação
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+0b101")); // binário
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - espaços em locais incorretos")
    void testIncorrectSpaces() {
        // Espaços em qualquer lugar causam erro na implementação atual
        assertThrows(NumberFormatException.class, () -> calculator.evaluate(" 5+10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5 +10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+ 10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+10 "));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5 + 10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("  5  +  10  "));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - operadores com caracteres")
    void testOperatorsWithCharacters() {
        // Testa operadores misturados com caracteres inválidos
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5*10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5/10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5-10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5%10"));
        
        // Nota: A implementação tem bugs com múltiplos + consecutivos
        // devido à falta de chaves no for loop, causando comportamento inconsistente
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - strings com palavras")
    void testWordStrings() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("cinco+dez"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("um+dois+três"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("hello+world"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("test+123"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("123+test"));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - caracteres Unicode")
    void testUnicodeCharacters() {
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+10α"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("β5+10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+π"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("∑+123"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+10€"));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - strings muito longas")
    void testVeryLongStrings() {
        // String muito longa com caracteres inválidos
        StringBuilder longInvalidString = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longInvalidString.append("a");
        }
        assertThrows(NumberFormatException.class, () -> 
            calculator.evaluate(longInvalidString.toString()));
        
        // String com muitos números mas formato inválido
        StringBuilder longString = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            longString.append("1+");
        }
        longString.append("invalid");
        assertThrows(NumberFormatException.class, () -> 
            calculator.evaluate(longString.toString()));
    }
    
    @Test
    @DisplayName("Teste de dados inválidos - casos extremos de entrada")
    void testExtremeInputCases() {
        // Apenas operadores - devido ao bug, alguns não geram exceção
        assertEquals(0, calculator.evaluate("+++")); // Bug: ["", "", "", ""] = 0 + 0 + 0 + 0 = 0
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("---"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("***"));
        
        // Parênteses (não suportados)
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("(5+10)"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5+(10+20)"));
        
        // Tabs e quebras de linha
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5\t+\t10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5\n+\n10"));
        assertThrows(NumberFormatException.class, () -> calculator.evaluate("5\r\n+10"));
    }
}
