package uy.viruscontrol.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;



public final class CaracteresDeEscapePersonalizados extends CharacterEscapes {
	
	private static final long serialVersionUID = 1L;
	private static final int[] esc;
    static{
        esc  = CharacterEscapes.standardAsciiEscapesForJSON();
        esc[(int)'<'] = CharacterEscapes.ESCAPE_CUSTOM;
        esc[(int)'>'] = CharacterEscapes.ESCAPE_CUSTOM;
        esc[(int)'&'] = CharacterEscapes.ESCAPE_CUSTOM;
        esc[(int)'\''] = CharacterEscapes.ESCAPE_CUSTOM;
        esc[(int)'\"'] = CharacterEscapes.ESCAPE_CUSTOM;
    }
    
	public CaracteresDeEscapePersonalizados() {
		super();
	}
	
	 @Override
	public int[] getEscapeCodesForAscii() {
         return esc;
     }

     @Override
     public SerializableString getEscapeSequence(final int i) {
         return new  SerializableString() {

             private final String stringValue = Character.toString((char) i);
             
             @Override
             public String getValue() {
                 return org.apache.commons.text.StringEscapeUtils.escapeXml11(stringValue);
             }

             @Override
             public int charLength() {
                 return stringValue.length();
             }

             @Override
             public char[] asQuotedChars() {
                 return new char[0];
             }

             @Override
             public byte[] asUnquotedUTF8() {
                 return new byte[0];
             }

             @Override
             public byte[] asQuotedUTF8() {
                 return new byte[0];
             }

			@Override
			public int appendQuotedUTF8(byte[] buffer, int offset) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int appendQuoted(char[] buffer, int offset) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int appendUnquotedUTF8(byte[] buffer, int offset) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int appendUnquoted(char[] buffer, int offset) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int writeQuotedUTF8(OutputStream out) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int writeUnquotedUTF8(OutputStream out) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int putQuotedUTF8(ByteBuffer buffer) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int putUnquotedUTF8(ByteBuffer out) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

         };
     }
}
