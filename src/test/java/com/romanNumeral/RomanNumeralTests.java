package com.romanNumeral;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import com.vev.romanNumeral.RomanNumeral;

public class RomanNumeralTests {
    
    private RomanNumeral romanNumeral;
    
    @BeforeEach
    void setUp() {
        romanNumeral = new RomanNumeral();
    }
    
    @Test
    @DisplayName("Teste de conversão de numerais romanos básicos")
    void testBasicRomanNumerals() {
        assertEquals(1, romanNumeral.convert("I"));
        assertEquals(5, romanNumeral.convert("V"));
        assertEquals(10, romanNumeral.convert("X"));
        assertEquals(50, romanNumeral.convert("L"));
        assertEquals(100, romanNumeral.convert("C"));
        assertEquals(500, romanNumeral.convert("D"));
        assertEquals(1000, romanNumeral.convert("M"));
    }
    
    @Test
    @DisplayName("Teste de conversão com adição simples")
    void testSimpleAddition() {
        assertEquals(2, romanNumeral.convert("II"));
        assertEquals(3, romanNumeral.convert("III"));
        assertEquals(6, romanNumeral.convert("VI"));
        assertEquals(7, romanNumeral.convert("VII"));
        assertEquals(8, romanNumeral.convert("VIII"));
        assertEquals(11, romanNumeral.convert("XI"));
        assertEquals(12, romanNumeral.convert("XII"));
        assertEquals(15, romanNumeral.convert("XV"));
        assertEquals(20, romanNumeral.convert("XX"));
        assertEquals(30, romanNumeral.convert("XXX"));
    }
    
    @Test
    @DisplayName("Teste de conversão com subtração")
    void testSubtraction() {
        assertEquals(4, romanNumeral.convert("IV"));
        assertEquals(9, romanNumeral.convert("IX"));
        assertEquals(40, romanNumeral.convert("XL"));
        assertEquals(90, romanNumeral.convert("XC"));
        assertEquals(400, romanNumeral.convert("CD"));
        assertEquals(900, romanNumeral.convert("CM"));
    }
    
    @Test
    @DisplayName("Teste de conversão com números complexos")
    void testComplexNumbers() {
        assertEquals(14, romanNumeral.convert("XIV"));
        assertEquals(19, romanNumeral.convert("XIX"));
        assertEquals(24, romanNumeral.convert("XXIV"));
        assertEquals(44, romanNumeral.convert("XLIV"));
        assertEquals(49, romanNumeral.convert("XLIX"));
        assertEquals(94, romanNumeral.convert("XCIV"));
        assertEquals(99, romanNumeral.convert("XCIX"));
        assertEquals(444, romanNumeral.convert("CDXLIV"));
        assertEquals(494, romanNumeral.convert("CDXCIV"));
        assertEquals(944, romanNumeral.convert("CMXLIV"));
        assertEquals(999, romanNumeral.convert("CMXCIX"));
    }
    
    @Test
    @DisplayName("Teste de conversão com anos históricos")
    void testHistoricalYears() {
        assertEquals(1984, romanNumeral.convert("MCMLXXXIV"));
        assertEquals(1994, romanNumeral.convert("MCMXCIV"));
        assertEquals(2000, romanNumeral.convert("MM"));
        assertEquals(2024, romanNumeral.convert("MMXXIV"));
    }
    
    @Test
    @DisplayName("Teste de conversão com números grandes")
    void testLargeNumbers() {
        assertEquals(1500, romanNumeral.convert("MD"));
        assertEquals(1600, romanNumeral.convert("MDC"));
        assertEquals(1700, romanNumeral.convert("MDCC"));
        assertEquals(1800, romanNumeral.convert("MDCCC"));
        assertEquals(1900, romanNumeral.convert("MCM"));
        assertEquals(3000, romanNumeral.convert("MMM"));
        assertEquals(3999, romanNumeral.convert("MMMCMXCIX"));
    }
    
    @Test
    @DisplayName("Teste de casos extremos com numerais repetidos")
    void testRepeatedNumerals() {
        assertEquals(4000, romanNumeral.convert("MMMM"));
        assertEquals(4, romanNumeral.convert("IIII"));
        assertEquals(300, romanNumeral.convert("CCC"));
        assertEquals(30, romanNumeral.convert("XXX"));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - caracteres não romanos")
    void testInvalidCharacters() {
        // Testa comportamento com caracteres que não são numerais romanos
        assertThrows(Exception.class, () -> romanNumeral.convert("A"));
        assertThrows(Exception.class, () -> romanNumeral.convert("B"));
        assertThrows(Exception.class, () -> romanNumeral.convert("Z"));
        assertThrows(Exception.class, () -> romanNumeral.convert("123"));
        assertThrows(Exception.class, () -> romanNumeral.convert("IX5"));
        assertThrows(Exception.class, () -> romanNumeral.convert("abc"));
        assertThrows(Exception.class, () -> romanNumeral.convert("@#$"));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - string vazia e nula")
    void testEmptyAndNullValues() {
        // A implementação atual retorna 0 para string vazia (não gera exceção)
        assertEquals(0, romanNumeral.convert(""));
        
        // Testa comportamento com string nula (pode gerar NullPointerException)
        assertThrows(NullPointerException.class, () -> romanNumeral.convert(null));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - letras minúsculas")
    void testLowercaseLetters() {
        // Numerais romanos devem ser em maiúsculas
        assertThrows(Exception.class, () -> romanNumeral.convert("i"));
        assertThrows(Exception.class, () -> romanNumeral.convert("v"));
        assertThrows(Exception.class, () -> romanNumeral.convert("x"));
        assertThrows(Exception.class, () -> romanNumeral.convert("iv"));
        assertThrows(Exception.class, () -> romanNumeral.convert("ix"));
        assertThrows(Exception.class, () -> romanNumeral.convert("mcmlxiv"));
    }
    
    @Test
    @DisplayName("Teste de valores inválidos - combinações mistas")
    void testMixedInvalidCombinations() {
        // Testa combinações de caracteres válidos e inválidos
        assertThrows(Exception.class, () -> romanNumeral.convert("IVa"));
        assertThrows(Exception.class, () -> romanNumeral.convert("X3"));
        assertThrows(Exception.class, () -> romanNumeral.convert("M-C"));
        assertThrows(Exception.class, () -> romanNumeral.convert("I V"));  // com espaço
        assertThrows(Exception.class, () -> romanNumeral.convert("X.I"));  // com ponto
        assertThrows(Exception.class, () -> romanNumeral.convert("I,V"));  // com vírgula
    }
    
    @Test
    @DisplayName("Teste de formatos tecnicamente incorretos - mas aceitos pela implementação")
    void testIncorrectButAcceptedSequences() {
        // A implementação atual não valida as regras clássicas de numerais romanos
        // Ela simplesmente processa os caracteres sequencialmente
        
        // Mais de 3 caracteres consecutivos iguais (tecnicamente incorreto, mas processado)
        assertEquals(5, romanNumeral.convert("IIIII"));    // 5 I's = 5
        assertEquals(50, romanNumeral.convert("XXXXX"));   // 5 X's = 50  
        assertEquals(500, romanNumeral.convert("CCCCC"));  // 5 C's = 500
        
        // Subtrações tecnicamente inválidas (mas processadas pela implementação)
        assertEquals(49, romanNumeral.convert("IL"));      // I antes de L = 50-1 = 49
        assertEquals(99, romanNumeral.convert("IC"));      // I antes de C = 100-1 = 99
        assertEquals(999, romanNumeral.convert("IM"));     // I antes de M = 1000-1 = 999
        assertEquals(490, romanNumeral.convert("XD"));     // X antes de D = 500-10 = 490
        assertEquals(990, romanNumeral.convert("XM"));     // X antes de M = 1000-10 = 990
    }
}
